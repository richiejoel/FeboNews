package com.richiejoel.febonews.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.richiejoel.febonews.R
import com.richiejoel.febonews.data.model.Articles
import com.richiejoel.febonews.databinding.FragmentNewsBinding
import com.richiejoel.febonews.ui.view.adapters.NewsAdapter
import com.richiejoel.febonews.ui.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel:NewsViewModel by activityViewModels()

    private lateinit var obRecyclerView: RecyclerView
    private lateinit var obAdapter: NewsAdapter
    private var articles: List<Articles> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNews()
        observerGetNews()
        mLoadRecycler()

    }

    private fun mLoadRecycler(){
        obRecyclerView = binding.recyclerNews
        obRecyclerView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        obRecyclerView.layoutManager = mLayoutManager
        obAdapter = NewsAdapter(articles, requireContext(), object:
            NewsAdapter.OnItemClickListener{
                override fun onItemClick(news: Articles, position: Int) {
                    viewModel.setNewSelected(news)
                    findNavController().navigate(R.id.action_newsFragment_to_newsDetailFragment)
                }
            }
        )
        obRecyclerView.adapter = obAdapter
    }

    private fun observerGetNews(){
        viewModel.newsModel.observe(viewLifecycleOwner) {
            articles = it?.articles!!
            obAdapter.setData(articles)
        }
    }


}