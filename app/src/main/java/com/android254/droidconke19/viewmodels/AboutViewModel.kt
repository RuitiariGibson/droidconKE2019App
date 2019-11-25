package com.android254.droidconke19.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android254.droidconke19.datastates.FirebaseResult
import com.android254.droidconke19.models.AboutDetailsModel
import com.android254.droidconke19.repository.AboutDetailsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AboutViewModel(private val aboutDetailsRepo: AboutDetailsRepo) : ViewModel() {
    private val detailsStateMediatorLiveData = MediatorLiveData<List<AboutDetailsModel>>()
    private val detailsError = MediatorLiveData<String>()
    private val organizersMediatorLiveData = MediatorLiveData<List<AboutDetailsModel>>()
    private val organizersError = MediatorLiveData<String>()
    private val sponsorsMediatorLiveData = MediatorLiveData<List<AboutDetailsModel>>()
    private val sponsorsError = MediatorLiveData<String>()


    fun getAboutDetailsResponse(): LiveData<List<AboutDetailsModel>> = detailsStateMediatorLiveData

    fun getAboutDetailsError(): LiveData<String> = detailsError

    fun getOrganizersResponse(): LiveData<List<AboutDetailsModel>> = organizersMediatorLiveData

    fun getOrganizerError(): LiveData<String> = organizersError

    fun getSponsorsResponse(): LiveData<List<AboutDetailsModel>> = sponsorsMediatorLiveData

    fun getSponsorsError(): LiveData<String> = sponsorsError

    fun fetchAboutDetails(aboutType: String, scope: CoroutineScope = viewModelScope) {
        scope.launch {
            when (val value = aboutDetailsRepo.getAboutDetails(aboutType)) {
                is FirebaseResult.Success -> detailsStateMediatorLiveData.postValue(value.data)
                is FirebaseResult.Error -> detailsError.postValue(value.exception)
            }
        }
    }

    fun getOrganizers(aboutType: String, scope: CoroutineScope = viewModelScope) {
        scope.launch {
            when (val value = aboutDetailsRepo.getAboutDetails(aboutType)) {
                is FirebaseResult.Success -> organizersMediatorLiveData.postValue(value.data)
                is FirebaseResult.Error -> organizersError.postValue(value.exception)
            }
        }

    }

    fun getSponsors(aboutType: String, scope: CoroutineScope = viewModelScope) {
        scope.launch {
            when (val value = aboutDetailsRepo.getAboutDetails(aboutType)) {
                is FirebaseResult.Success -> sponsorsMediatorLiveData.postValue(value.data)
                is FirebaseResult.Error -> sponsorsError.postValue(value.exception)
            }
        }
    }

}
