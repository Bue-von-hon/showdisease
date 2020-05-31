package com.kc.showdisease;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DiseaseViewModel extends AndroidViewModel {
    private DiseaseRepository repository;
    private LiveData<List<Disease>> allDisease;

    public DiseaseViewModel(@NonNull Application application) {
        super(application);
        repository = new DiseaseRepository(application);
        allDisease = repository.getAllDisease();
    }

    public void insert(Disease disease) {
        repository.insert(disease);
    }

    public void update(Disease disease) {
        repository.update(disease);
    }

    public void delete(Disease disease) {
        repository.delete(disease);
    }

    public LiveData<List<Disease>> getAllDisease() {
        return allDisease;
    }
}
