package com.richiejoel.febonews.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.richiejoel.febonews.R
import com.richiejoel.febonews.data.model.Articles
import com.richiejoel.febonews.databinding.FragmentNewsBinding
import com.richiejoel.febonews.ui.view.adapters.NewsAdapter
import com.richiejoel.febonews.ui.viewModel.NewsViewModel
import com.richiejoel.febonews.utils.UtilsView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel:NewsViewModel by activityViewModels()

    private lateinit var obRecyclerView: RecyclerView
    private lateinit var obAdapter: NewsAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private var articles: ArrayList<Articles> = arrayListOf<Articles>()

    private var isLoadingScroll = false
    private var isScroll = false
    private var currentItem = -1
    private var totalItem = -1
    private var scrollOutItem = -1
    private var isAllVideoLoaded = false

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
        observerScroll()
        mLoadRecycler()
        mListenerRecycler()

    }

    private fun mLoadRecycler(){
        obRecyclerView = binding.recyclerNews
        obRecyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(context)
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

    private fun mListenerRecycler(){
        obRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScroll = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = mLayoutManager.childCount
                totalItem =  mLayoutManager.itemCount
                scrollOutItem = mLayoutManager.findFirstVisibleItemPosition()
                if (isScroll && (currentItem + scrollOutItem == totalItem)){
                    isScroll = false
                    if (!isLoadingScroll){
                        if (!isAllVideoLoaded){
                            viewModel.currentNews++
                            if(viewModel.currentNews <= viewModel.newsModel.value?.totalResults!!){
                                viewModel.getNews(viewModel.currentNews.toString())
                            } else {
                                viewModel.isAllVideoLoaded.postValue(true)
                            }
                        } else {
                            Toast.makeText(requireContext(), "All video loaded", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        })
    }

    private fun observerGetNews(){
        viewModel.newsModel.observe(viewLifecycleOwner) {
            articles = (it?.articles as ArrayList<Articles>?)!!
            obAdapter.setData(articles)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
    }

    private fun observerScroll(){
        viewModel?.isLoadingScroll?.observe(viewLifecycleOwner) {
            isLoadingScroll = it
            binding.progressBarInfinite.visibility =
                if (isLoadingScroll) View.VISIBLE else View.GONE
        }

        viewModel?.isAllVideoLoaded?.observe(viewLifecycleOwner) {
            isAllVideoLoaded = it
            if (it) Toast.makeText(
                requireContext(),
                "All video has been loaded",
                Toast.LENGTH_SHORT
            ).show()
        }
    }



}