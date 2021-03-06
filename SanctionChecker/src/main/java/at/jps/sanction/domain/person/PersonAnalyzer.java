/*******************************************************************************
 * Copyright (c) 2015 Jim Fandango (The Last Guy Coding) Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package at.jps.sanction.domain.person;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.jps.sanction.domain.SanctionAnalyzer;
import at.jps.sanction.model.Message;
import at.jps.sanction.model.MessageContent;
import at.jps.sanction.model.listhandler.SanctionListHandler;

public class PersonAnalyzer extends SanctionAnalyzer {

    static final Logger logger = LoggerFactory.getLogger(PersonAnalyzer.class);

    public PersonAnalyzer() {
        super();
    }

    boolean firstIteration = true; // for checks that have to only be done once

    // @Override
    // public void processMessage(final Message message) {
    //
    // boolean isHit = false;
    //
    // final long starttime = System.currentTimeMillis();
    // if (logger.isInfoEnabled()) {
    // logger.info("start check message: " + message.getUUID());
    // }
    //
    // firstIteration = true;
    // final AnalysisResult analyzeresult = new AnalysisResult(message);
    //
    // // go through all lists
    // // do a generic check
    // for (final SanctionListHandler listhandler : getStreamManager().getSanctionListHandlers().values()) {
    // if (logger.isInfoEnabled()) {
    // logger.info("start check against list: " + listhandler.getListName());
    // }
    //
    // genericListCheck(listhandler, analyzeresult);
    // firstIteration = false;
    //
    // if (logger.isInfoEnabled()) {
    // logger.info("finished check against list: " + listhandler.getListName());
    // }
    // }
    //
    // isHit = (analyzeresult.getHitList().size() > 0);
    //
    // final long stoptime = System.currentTimeMillis();
    // final long difftime = stoptime - starttime;
    //
    // analyzeresult.setAnalysisStartTime(starttime);
    // analyzeresult.setAnalysisStopTime(stoptime);
    //
    // if (isHit) {
    // if (logger.isInfoEnabled()) {
    // logger.info(" message has hits: " + message.getUUID());
    // }
    //
    // final ProcessStep processStep = new ProcessStep();
    // processStep.setRemark(ProcessStep.ProcessStatus.CHECK.name());
    // analyzeresult.addProcessStep(processStep);
    //
    // // getStreamManager().addToHitList(analyzeresult);
    // getHitQueue().addMessage(analyzeresult);
    // }
    // else {
    // if (logger.isInfoEnabled()) {
    // logger.info(" message has NO hits: " + message.getUUID());
    // }
    // // getStreamManager().addToNoHitList(analyzeresult);
    // getNoHitQueue().addMessage(analyzeresult);
    // }
    //
    // if (logger.isInfoEnabled()) {
    // logger.info("stop checking message: " + message.getUUID() + " (Time needed : " + difftime + " ms)");
    // }
    //
    // }

    public static MessageContent getFieldsToCheckInternal(final Message message) {

        MessageContent messageContent = message.getMessageContent();

        if (messageContent == null) {
            messageContent = new MessageContent();

            final HashMap<String, String> fieldsAndValues = new HashMap<>();
            messageContent.setFieldsAndValues(fieldsAndValues);

            messageContent.setMessageType("IPerson"); // <---> this a hardcoded alternate fact

            final String msgText = message.getRawContent();

            // for now : <header> "|" <content> all "," separated

            final String lines[] = new String[2];
            lines[0] = msgText.substring(0, msgText.indexOf('|'));
            lines[1] = msgText.substring(msgText.indexOf('|') + 1);
            final String[] headerline = lines[0].split("," + "(?=([^\"]|\"[^\"]*\")*$)", -1);
            final String[] contentline = lines[1].split("," + "(?=([^\"]|\"[^\"]*\")*$)", -1);

            int ix = 0;
            for (final String header : headerline) {
                fieldsAndValues.put(header, contentline[ix]);
                ix++;
            }

            //
            //
            // final StrTokenizer textTokenizer = new StrTokenizer(msgText, ","); // TODO hardcoded delimiter
            // textTokenizer.setEmptyTokenAsNull(false);
            // textTokenizer.setIgnoreEmptyTokens(false);
            //
            // int i = -2; // TODO: there are currently 2 dummy columns in our testsample ( list & id )
            // while (textTokenizer.hasNext()) {
            // final String value = textTokenizer.next();
            // if ((value != null) && (value.length() > 0) && (i >= 0)) {
            // fieldsAndValues.put(PersonMessage.fieldNames[i], value);
            // }
            // i++;
            // }
            message.setMessageContent(messageContent);
        }
        return messageContent;
    }

    @Override
    public MessageContent getFieldsToCheck(final Message message) {

        return getFieldsToCheckInternal(message);
    }

    protected boolean isFieldToCheck(final String msgFieldName, final String watchlist, final String indexname) {
        final boolean checkit = (getStreamManager().isFieldToCheck(msgFieldName, indexname, watchlist, "Person"));

        return checkit;
    }

    protected boolean isFieldToCheckFuzzy(final String msgFieldName, final SanctionListHandler listhandler) {
        final boolean fuzzy = listhandler.isFuzzySearch() && getStreamManager().isField2Fuzzy(msgFieldName, "Person");

        return fuzzy;
    }

    // private void genericListCheck(final SanctionListHandler listhandler, final AnalysisResult analyzeresult) {
    //
    // if ((listhandler.getEntityList() != null) && !listhandler.getEntityList().isEmpty()) {
    //
    // final MessageContent messageContent = getFieldsToCheck(analyzeresult.getMessage());
    //
    // if (messageContent != null) {
    //
    // // first we check the wholename field
    //
    // final String msgFieldText = messageContent.getFieldsAndValues().get(PersonMessage.fieldNames[0]); // "wholename"
    //
    // final List<String> msgFieldTokens = TokenTool.getTokenList(msgFieldText, listhandler.getDelimiters(), listhandler.getDeadCharacters(),
    // getStreamManager().getMinTokenLen(listhandler.getListName()), getStreamManager().getStopwordList().getValues(), false);
    //
    // for (final WL_Entity entity : listhandler.getEntityList()) {
    // for (final WL_Name name : entity.getNames()) {
    // final List<String> nameTokens = TokenTool.getTokenList(name.getWholeName(), listhandler.getDelimiters(), listhandler.getDeadCharacters(),
    // getStreamManager().getMinTokenLen(PersonMessage.fieldNames[0]), getStreamManager().getIndexAusschlussList().getValues(), true);
    //
    // float totalHitRateRelative = 0;
    // int totalHitRateAbsolute = 0;
    // int totalHitRatePhrase = 0;
    //
    // for (final String msgFieldToken : msgFieldTokens) {
    //
    // for (final String nameToken : nameTokens) {
    //
    // // >>>>> THE COMPARISION <<<<<
    //
    // final float hitValue = TokenTool.compareCheck(nameToken, msgFieldToken, isFieldToCheckFuzzy(PersonMessage.fieldNames[0], listhandler),
    // getStreamManager().getMinTokenLen(PersonMessage.fieldNames[0]), getStreamManager().getFuzzyValue(PersonMessage.fieldNames[0]));
    //
    // // single fuzzy limit !!
    // if (hitValue > getStreamManager().getMinRelVal(PersonMessage.fieldNames[0])) {// TODO: this should be on list-base
    // totalHitRateRelative += hitValue;
    //
    // final SanctionHitInfo swhi = new SanctionHitInfo(listhandler.getListName(), entity.getWL_Id(), nameToken, PersonMessage.fieldNames[0], msgFieldToken,
    // (int) hitValue);
    //
    // // single word hit list if not already in
    // if (!analyzeresult.getHitTokensList().contains(swhi)) {
    // analyzeresult.getHitTokensList().add(swhi);
    // }
    // }
    // // if (hitValue == 100) {
    // // totalHitRateAbsolute += hitValue;
    // // // hitValue = TokenTool.compareCheck(nameToken, msgFieldToken, false, getStreamManager().getMinTokenLen());
    // // // totalHitRateAbsolute += hitValue;
    // // // if (logger.isDebugEnabled()) logger.debug("compare : " + nameToken + " <-> " + msgFieldToken + " (" + hitValue + ")");
    // // }
    //
    // // final float hitValue = TokenTool.compareCheck(nameToken, msgFieldToken, listhandler.isFuzzySearch(), getStreamManager().getMinTokenLen(),
    // // getStreamManager().getFuzzyValue());
    //
    // if (hitValue == 100) {
    // totalHitRateAbsolute += hitValue;
    // }
    // else if (hitValue > 79) {
    // totalHitRateRelative += hitValue;
    // }
    // }
    // }
    //
    // boolean addPhrase = false;
    // boolean addAbs = false;
    // boolean addRel = false;
    //
    // // kleineres in gr��eres wosnsunst...
    // final int minTokens = Math.min(msgFieldTokens.size(), nameTokens.size());
    //
    // if (minTokens > 0) {
    //
    // if (minTokens > 1) {
    // // eval simple text in text search for
    // // phrase
    // // check
    // // but only if both sides contain more than one token
    // final boolean contains = TokenTool.checkContains(msgFieldTokens, nameTokens, " "); // TODO: this is no goood sol in gen
    // if (contains) {
    //
    // totalHitRatePhrase = 100 * minTokens;
    // if ((totalHitRatePhrase / minTokens) > getStreamManager().getMinAbsVal(PersonMessage.fieldNames[3])) { // TODO: this should be on list-base
    //
    // logger.debug("PHRASECHECK: " + msgFieldText + ": " + name.getWholeName() + " --> " + contains);
    //
    // addPhrase = true;
    // }
    // }
    // }
    // else {
    // // no hit but if other hit we go
    // // totalHitRatePhrase = 100;
    // }
    // // hits for one field
    // // TODO: this is a dummy implementation
    // if ((totalHitRateRelative / minTokens) > getStreamManager().getMinRelVal(PersonMessage.fieldNames[3])) { // TODO: this should be on list-base
    //
    // if (logger.isDebugEnabled()) {
    // final String keys = TokenTool.buildTokenString(nameTokens, " ");
    //
    // logger.debug("RELATIVE SUM: " + msgFieldText + ": " + keys + " --> " + totalHitRateRelative);
    // }
    //
    // addRel = true;
    //
    // }
    //
    // if ((totalHitRateAbsolute / minTokens) > getStreamManager().getMinAbsVal(PersonMessage.fieldNames[3])) {// TODO: this should be on list-base
    //
    // if (logger.isDebugEnabled()) {
    // final String keys = TokenTool.buildTokenString(nameTokens, " ");
    //
    // logger.debug("ABSOLUTE SUM: " + msgFieldText + ": " + keys + " --> " + totalHitRateAbsolute);
    // }
    //
    // addAbs = true;
    //
    // }
    // else {
    // // if (((totalHitRateAbsolute == 100) &&
    // // (keyTokens.size() ==1)) ||
    // // ((totalHitRateAbsolute > 100) &&
    // // (keyTokens.size() >1)))
    // // System.out.println(field
    // // +" <-> "+TokenComparer
    // // .buildTokenString(keyTokens, " ")
    // // +totalHitRateAbsolute);
    // }
    //
    // // add cumulated hit
    // if ((addAbs) || (addPhrase) || (addRel)) {
    //
    // // remove single word hits which are not relevant
    //
    // final ValueListHandler notSingleWordListHandler = getStreamManager().getNotSingleWordHitList();
    // int nrOfNotSowords = 0;
    // if (notSingleWordListHandler != null) {
    // nrOfNotSowords = TokenTool.compareTokenLists(notSingleWordListHandler.getValues(), nameTokens);
    // }
    //
    // if ((totalHitRateAbsolute > (nrOfNotSowords * 100)) || (totalHitRateRelative > (nrOfNotSowords * 100)) || (totalHitRatePhrase > (nrOfNotSowords * 100))) {
    //
    // final SanctionHitResult hr = new SanctionHitResult();
    //
    // final boolean addHit = true;
    //
    // // // check optimizer if we have to cleanup some mess ;-)
    // // if (getStreamManager().getTxNoHitOptimizationListHandler() != null) {
    // // for (final OptimizationRecord optimizationRecord : getStreamManager().getTxNoHitOptimizationListHandler().getValues()) {
    // // if (optimizationRecord.getWatchListName().equals(listhandler.getListName()) && optimizationRecord.getWatchListId().equals(entity.getWL_Id())
    // // && optimizationRecord.getFieldName().equals(msgFieldName) && optimizationRecord.getToken().equals(msgFieldText))
    // // // TODO: make this more
    // // // versatile !!
    // // {
    // // // or remove hit on "Confirmed" level
    // // if (getStreamManager().getTxNoHitOptimizationListHandler().isAutoDiscardHitsOnConfirmStatus()) {
    // // // remove it ;-)
    // // addHit = false;
    // // }
    // // else {
    // // hr.setHitOptimized(optimizationRecord.getStatus());
    // // }
    // // break;
    // // }
    // // }
    // // }
    // if (addHit) {
    //
    // hr.setHitField(PersonMessage.fieldNames[3]); // TODO : this is hardcoded
    // hr.setHitDescripton(name.getWholeName());
    // hr.setHitListName(listhandler.getListName());
    // hr.setHitListPriority(listhandler.getOrderNr());
    // hr.setHitId(entity.getWL_Id());
    // hr.setHitLegalBasis(entity.getLegalBasis());
    // hr.setHitExternalUrl(entity.getInformationUrl());
    // hr.setHitType(listhandler.getType());
    // hr.setEntityType(entity.getEntityType().getText());
    // hr.setHitRemark(entity.getComment());
    //
    // hr.setAbsolutHit(totalHitRateAbsolute);
    // hr.setPhraseHit(totalHitRatePhrase);
    // hr.setRelativeHit((int) totalHitRateRelative);
    //
    // analyzeresult.addHitResult(hr);
    // }
    // else {
    // if (logger.isDebugEnabled()) {
    // logger.debug("Optimizer: " + msgFieldText + " -> " + name.getWholeName() + " : hit removed due Confirmed as NOT NEEDED!");
    // }
    // }
    // }
    // else {
    // // if (logger.isDebugEnabled()) {
    // // logger.debug("NSWH: " + msgFieldText + ": hit removed due to single word hit!");
    // // }
    // }
    // }
    // }
    // else {
    // // System.out.println("leereListe breakpoint");
    // }
    // }
    // }
    // }
    // }
    // }
}
