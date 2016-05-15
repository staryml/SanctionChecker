package at.jps.sanction.domain.person.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.jps.sanction.model.AnalysisResult;
import at.jps.sanction.model.queue.Queue;
import at.jps.sanction.model.worker.PostNoHitWorker;

public class PersonPostNoHitWorker extends PostNoHitWorker {

    static final Logger logger = LoggerFactory.getLogger(PersonPostNoHitWorker.class);

    @Override
    public void handleMessage(final AnalysisResult message) {

        // add for further Processing
        final Queue<AnalysisResult> outputQueue = getOutQueue();
        if (outputQueue != null) {
            outputQueue.addMessage(message);
        }

    }

}