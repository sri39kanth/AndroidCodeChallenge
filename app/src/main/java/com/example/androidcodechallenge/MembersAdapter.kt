package com.example.androidcodechallenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MembersAdapter
constructor(var context : Context ,var membersList: List<MembersData>,var membersAdapterListener: MembersAdapterListener ) : RecyclerView.Adapter<MembersAdapter.MemberRecyclerViewHolder>() {

    inner class MemberRecyclerViewHolder(view : View) :  RecyclerView.ViewHolder(view) {
        val membersImageView by lazy{ view.findViewById<AppCompatImageView>(R.id.member_image_view) }
        val nameTextView by lazy { view.findViewById<TextView>(R.id.name_text_view) }
        val memberTextView1 by lazy {   view.findViewById<TextView>(R.id.member_detail_text1) }
        val memberTextView2 by lazy { view.findViewById<TextView>(R.id.member_detail_text2) }
        val memberacceptingTextView by lazy { view.findViewById<TextView>(R.id.button_accepting_text) }
        val acceptButton by lazy { view.findViewById<Button>(R.id.accept_button) }
        val declineButton by lazy { view.findViewById<Button>(R.id.decline_button) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberRecyclerViewHolder {
        return MemberRecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.members_adapter_layout, parent, false))
    }

    override fun getItemCount(): Int = membersList.size

    override fun onBindViewHolder(holder: MemberRecyclerViewHolder, position: Int) {
        val membersData = membersList[position]
        membersData.medium?.let {
            Glide.with(context)
                .load(it)
                .override(200,300)
                .centerCrop()
                .into(holder.membersImageView)
        }
        val nameText = "${membersData.title}. ${membersData.firstName}  ${membersData.lastName}"
        val text1 = "${membersData.age}, ${membersData.city}"
        val text2 = "${membersData.state}, ${membersData.country}, ${membersData.postcode}"
        holder.nameTextView.text = nameText
        holder.memberTextView1.text = text1
        holder.memberTextView2.text = text2
        membersData.status?.let {
            setTextForAcceptingTextView(it, holder, membersData)
           } ?: setButtonVisibility(holder, true)

        holder.acceptButton.setOnClickListener {
            membersData.status = "accepted"
            membersAdapterListener.acceptButtonClicked(membersData)
        }

        holder.declineButton.setOnClickListener {
            membersData.status = "declined"
            membersAdapterListener.declineButtonClicked(membersData)
        }

    }

    fun setTextForAcceptingTextView(value: String, holder: MemberRecyclerViewHolder, membersData: MembersData){
        var acceptedOrDeclinedText = ""
        when {
            value.equals( "accepted", true) -> acceptedOrDeclinedText = "${membersData.firstName} has been accepted"
            value.equals("declined",true) ->acceptedOrDeclinedText = "${membersData.firstName} has been declined"
            else -> setButtonVisibility(holder, true)
        }
        if(acceptedOrDeclinedText.isNotEmpty()) {
            setButtonVisibility(holder, false)
            holder.memberacceptingTextView.visibility = View.VISIBLE
            holder.memberacceptingTextView.text = acceptedOrDeclinedText
        }

    }

    fun setButtonVisibility(holder: MemberRecyclerViewHolder, isVisible: Boolean) {
        holder.acceptButton.visibility = if(isVisible) View.VISIBLE else View.GONE
        holder.declineButton.visibility = if(isVisible) View.VISIBLE else View.GONE
    }

    interface MembersAdapterListener {
        fun acceptButtonClicked(membersData: MembersData)
        fun declineButtonClicked(membersData: MembersData)
    }

}