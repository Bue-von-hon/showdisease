package com.kc.showdisease;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DiseaseRepository {
    private DiseaseDao diseaseDao;
    private LiveData<List<Disease>> allDisease;

    public DiseaseRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        diseaseDao = appDatabase.diseaseDao();
        allDisease = diseaseDao.LgetAll();
    }

    public void insert(Disease disease) {
        new InsertDiseaseAsyncTask(diseaseDao).execute(disease);
    }

    public void update(Disease disease) {
        new UpdateDiseaseAsyncTask(diseaseDao).execute(disease);
    }

    public void delete(Disease disease) {
        new DeleteDiseaseAsyncTask(diseaseDao).execute(disease);
    }

    public LiveData<List<Disease>> getAllDisease() {
        return allDisease;
    }

    private static class InsertDiseaseAsyncTask extends AsyncTask<Disease, Void, Void> {
        private DiseaseDao diseaseDao;

        private InsertDiseaseAsyncTask(DiseaseDao diseaseDao) {
            this.diseaseDao = diseaseDao;
        }

        @Override
        protected Void doInBackground(Disease... diseases) {
            diseaseDao.insert(diseases[0]);
            return null;
        }
    }

    private static class UpdateDiseaseAsyncTask extends AsyncTask<Disease, Void, Void> {
        private DiseaseDao diseaseDao;

        private UpdateDiseaseAsyncTask(DiseaseDao diseaseDao) {
            this.diseaseDao = diseaseDao;
        }

        @Override
        protected Void doInBackground(Disease... diseases) {
            diseaseDao.update(diseases[0]);
            return null;
        }
    }

    private static class DeleteDiseaseAsyncTask extends AsyncTask<Disease, Void, Void> {
        private DiseaseDao diseaseDao;

        private DeleteDiseaseAsyncTask(DiseaseDao diseaseDao) {
            this.diseaseDao = diseaseDao;
        }

        @Override
        protected Void doInBackground(Disease... diseases) {
            diseaseDao.delete(diseases[0]);
            return null;
        }
    }

}
