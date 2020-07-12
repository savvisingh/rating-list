package com.example.getyourguide.ui.userrating

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getyourguide.BR
import com.example.getyourguide.R
import com.example.getyourguide.databinding.MainFragmentBinding
import com.example.getyourguide.ui.userrating.viewmodel.UserRatingsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RatingFragment : DaggerFragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = RatingFragment()
    }

    private lateinit var viewModel: UserRatingsViewModel

    lateinit var binding: MainFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserRatingsViewModel::class.java)
        binding.setVariable(BR.vm, viewModel)
        binding.executePendingBindings()
        viewModel.fetchRatings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}