package com.alfonso.recipes.viewModel;

import androidx.lifecycle.ViewModel;

import com.alfonso.recipes.models.Step;

import java.util.List;

public class StepDetailViewModel extends ViewModel {
    private Step currentStep;
    private List<Step> listSteps;

    public StepDetailViewModel() {
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
}
