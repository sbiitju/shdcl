package com.shdclgroup.app.features.home

import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.shdclgroup.app.core.base.viewmodel.MviViewModel
import com.shdclgroup.app.core.base.widget.BaseViewState
import com.shdclgroup.app.core.extensions.mapSuccess
import com.shdclgroup.app.features.home.data.remote.DashBoardRepository
import com.shdclgroup.app.features.home.view.DashBoardEvent
import com.shdclgroup.app.features.home.view.DashBoardState
import com.shdclgroup.app.features.home.view.ScreenContent
import com.shdclgroup.app.prefs
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.content.Context


@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val repository: DashBoardRepository
) : MviViewModel<BaseViewState<DashBoardState>, DashBoardEvent>() {
     var data = DashBoardState(
        amount = "",
        description = "",
        projectID = "",
        expenseDate = "",
        screenContent = ScreenContent.Expense,
         listOfProject = emptyList(),
    )
    init {
        prefs.loginModel?.let {
            onTriggerEvent(
                DashBoardEvent.OnUserIDChanged(
                    it.userID
                )
            )
        }
        setState(BaseViewState.Data(DashBoardState(
            amount = "",
            description = "",
            projectID = "",
            expenseDate = "",
            screenContent = ScreenContent.Expense,
        )))
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProject().mapSuccess {
                if (it?.data?.isNotEmpty() == true){
                    setState(BaseViewState.Data(DashBoardState(listOfProject = it.data)))
                    data.listOfProject = it.data
                }
            }

        }
    }

    fun getProject() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProject().mapSuccess {
                if (it?.data?.isNotEmpty() == true){
                    setState(BaseViewState.Data(DashBoardState(listOfProject = it.data)))
                }
            }

        }
    }

    override fun onTriggerEvent(eventType: DashBoardEvent) {

        when (eventType) {
            is DashBoardEvent.OnSubmit -> {

                    if (eventType.screenContent == ScreenContent.Expense){
                        safeLaunch {
                            repository.submitExpense(
                                eventType.amount,
                                eventType.description,
                                eventType.projectID,
                                eventType.userID,
                                eventType.expenseDate,
                            ).mapSuccess {
                                setState(BaseViewState.Data(DashBoardState(
                                    amount = "",
                                    description = "",
                                    projectID = "",
                                    expenseDate = "",
                                    screenContent = ScreenContent.Expense,
                                )))
                            }
                            Toast.makeText(eventType.context, "Successfully expense Added", Toast.LENGTH_SHORT).show()

                        }

                    }else{
                        safeLaunch {
                            repository.submitIncome(
                                eventType.amount,
                                eventType.description,
                                eventType.projectID,
                                eventType.userID,
                                eventType.expenseDate
                            ).mapSuccess {
                                setState(BaseViewState.Data(DashBoardState(
                                    amount = "",
                                    description = "",
                                    projectID = "",
                                    expenseDate = "",
                                    screenContent = ScreenContent.Income,
                                )))
                                Toast.makeText(eventType.context, "Successfully income Added", Toast.LENGTH_SHORT).show()
                            }
                        }



                }
            }

            is DashBoardEvent.OnAmountChanged -> {
                data = data.copy(amount = eventType.amount)

                setState(BaseViewState.Data(DashBoardState(amount = eventType.amount)))
            }
            is DashBoardEvent.OnTitleChanged -> {
                data = data.copy(title = eventType.title)
                setState(BaseViewState.Data(DashBoardState(title = eventType.title)))
            }

            is DashBoardEvent.OnDescriptionChanged -> {
                data = data.copy(description = eventType.description)
                setState(BaseViewState.Data(DashBoardState(description = eventType.description)))
            }

            is DashBoardEvent.OnProjectIDChanged -> {
                data = data.copy(projectID = eventType.projectID)
                setState(BaseViewState.Data(DashBoardState(projectID = eventType.projectID)))
            }

            is DashBoardEvent.OnUserIDChanged -> {
                data = data.copy(userID = eventType.userID)
                setState(BaseViewState.Data(DashBoardState(userID = eventType.userID)))
            }

            is DashBoardEvent.OnExpenseDateChanged -> {
                data = data.copy(expenseDate = eventType.expenseDate)
                setState(BaseViewState.Data(DashBoardState(expenseDate = eventType.expenseDate)))
            }

            is DashBoardEvent.OnScreenContentChanged -> {
                data = data.copy(screenContent = eventType.screenContent)
                setState(BaseViewState.Data(DashBoardState(screenContent = eventType.screenContent)))
            }
        }
    }
}