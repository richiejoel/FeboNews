package com.richiejoel.febonews.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.richiejoel.febonews.R
import com.richiejoel.febonews.data.model.Articles
import com.richiejoel.febonews.databinding.FragmentNewsDetailBinding
import com.richiejoel.febonews.ui.viewModel.NewsViewModel
import com.richiejoel.febonews.utils.UtilsView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)

        observerNewSelected()

        return binding.root

    }

    private fun observerNewSelected(){
        viewModel.newsSelected.observe(viewLifecycleOwner) {
            it
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleDetail.text = viewModel.newsSelected.value?.title
        binding.dateDetail.text = viewModel.newsSelected.value?.publishedAt?.let { UtilsView.getFormatDateFromDate(it) }
        binding.descriptionNews.text = viewModel.newsSelected.value?.description + viewModel.newsSelected.value?.content
        binding.sourceDetail.text = "Source: ${viewModel.newsSelected.value?.source?.name}"
        Glide.with(requireContext()).load(viewModel.newsSelected.value?.urlToImage).into(binding.imgDetail)

        binding.goPage.setOnClickListener {
            findNavController().navigate(R.id.action_newsDetailFragment_to_webViewFragment)
        }

    }


}