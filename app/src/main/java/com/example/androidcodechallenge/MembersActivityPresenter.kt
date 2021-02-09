package com.example.androidcodechallenge

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MembersActivityPresenter {

    fun getMembersData(membersList: List<MembersData>,
                       insterDataIntoViewModelData: InsterDataIntoViewModelData,
    loadMembersInRecyclerView: LoadMembersInRecyclerView) {
         if(membersList.isEmpty()) {
             retrieveData(insterDataIntoViewModelData)
         }
        loadMembersInRecyclerView.populateRecyclerViewWithMembers(membersList)

    }


    private fun retrieveData(insterDataIntoViewModelData: InsterDataIntoViewModelData) {

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = NetworkClass.retrofit.getResponseData()
                if (response.isSuccessful && response.body() != null) {
                    insetDataintoDB(response, insterDataIntoViewModelData)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun insetDataintoDB(response: Response<UsersDataModel>, InsterDataIntoViewModelData: InsterDataIntoViewModelData) {
        response.body()?.results?.let {
            it.forEach { userModel ->
                InsterDataIntoViewModelData.insertIntoViewModel(
                    MembersData(
                        null,
                        userModel.gender,
                        userModel.name.title,
                        userModel.name.first,
                        userModel.name.last,
                        userModel.location.city,
                        userModel.location.state,
                        userModel.location.country,
                        userModel.location.postcode,
                        userModel.registered.phone,
                        userModel.dob.age,
                        userModel.picture.large,
                        userModel.picture.medium,
                        userModel.picture.thumbnail,
                        null
                    )
                )
            }
        }
    }


    interface InsterDataIntoViewModelData{
        fun insertIntoViewModel(membersData: MembersData)
    }


    interface LoadMembersInRecyclerView {
        fun populateRecyclerViewWithMembers(membersList: List<MembersData>)
    }
}
