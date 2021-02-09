package com.example.androidcodechallenge

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MembersActivity : AppCompatActivity() {
    private lateinit var viewModel: MembersViewModel

    private lateinit var presenter : MembersActivityPresenter
    private  val progressBar by lazy { findViewById<ProgressBar>(R.id.llprogress_bar) }

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.members_recycler_View) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)
        progressBar.visibility = View.VISIBLE
        instantiatePresenter()

        viewModel = ViewModelProviders.of(this).get(MembersViewModel::class.java)

        viewModel.members.observe(this, Observer {
            presenter.getMembersData(it, object : MembersActivityPresenter.InsterDataIntoViewModelData {
                override fun insertIntoViewModel(membersData: MembersData) {

                    viewModel.insert(membersData)
                }
            },
            object : MembersActivityPresenter.LoadMembersInRecyclerView {
                override fun populateRecyclerViewWithMembers(membersList: List<MembersData>) {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    populateRecyclerViewForMembers(membersList)
                }
            })
        })
    }

    fun instantiatePresenter() {
        presenter = MembersActivityPresenter()
    }




    fun populateRecyclerViewForMembers(membersList: List<MembersData>) {
        val adapter = MembersAdapter(this, membersList, memberAdapterlistener)
        recyclerView.layoutManager = LinearLayoutManager(this@MembersActivity)
        recyclerView.adapter = adapter
    }



    val memberAdapterlistener = object : MembersAdapter.MembersAdapterListener {
        override fun acceptButtonClicked(membersData: MembersData) {
            updateDataIntoDB(membersData)
        }

        override fun declineButtonClicked(membersData: MembersData) {
            updateDataIntoDB(membersData)
        }

    }

    fun updateDataIntoDB(membersData: MembersData) {
        viewModel.update(membersData)
    }
}