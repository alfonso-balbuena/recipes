package com.alfonso.recipes.viewModel;

import androidx.lifecycle.ViewModel;

import com.alfonso.recipes.models.Step;

import java.util.List;

public class StepDetailViewModel extends ViewModel {
    private Step currentStep;
    private List<Step> listSteps;
    private Long positionVideo;
    public StepDetailViewModel() {
        positionVideo = 0L;
    }


    public Step getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Step currentStep) {
        this.currentStep = currentStep;
    }

    public List<Step> getListSteps() {
        return listSteps;
    }

    public void setListSteps(List<Step> listSteps) {
        this.listSteps = listSteps;
    }

    public void next() {
        int position = currentStep.getNumberStep();
        if (++position < listSteps.size()) {
            currentStep = listSteps.get(position);
        }
    }

    public boolean isLast() {
        return currentStep.getNumberStep() == (listSteps.size() - 1);
    }

    public Long getPositionVideo() {
        return positionVideo;
    }

    public void setPositionVideo(Long positionVideo) {
        this.positionVideo = positionVideo;
    }
}
