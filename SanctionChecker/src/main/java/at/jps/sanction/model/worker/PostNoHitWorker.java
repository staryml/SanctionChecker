package at.jps.sanction.model.worker;

import at.jps.sanction.model.AnalysisResult;
import at.jps.sanction.model.MessageStatus;
import at.jps.sanction.model.worker.out.OutputWorker;

public class PostNoHitWorker extends OutputWorker {

    @Override
    public void handleMessage(AnalysisResult message) {
        message.setAnalysisStatus(MessageStatus.BUSY_POSTPROCESS);

    }

}
