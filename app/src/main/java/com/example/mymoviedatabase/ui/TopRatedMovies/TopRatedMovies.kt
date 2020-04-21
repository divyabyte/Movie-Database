package com.example.mymoviedatabase.ui.TopRatedMovies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.data.db.entities.TopRated
import com.example.mymoviedatabase.util.Coroutines
import com.example.mymoviedatabase.util.hide
import com.example.mymoviedatabase.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.top_rated_movies_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TopRatedMovies : Fragment() , KodeinAware {

    override val kodein by kodein()

    private val factory: TopRatedViewModelFactory by instance()

    private lateinit var viewModel: TopRatedMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_rated_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(TopRatedMoviesViewModel::class.java)

        bindUI()

    }


    private fun bindUI() = Coroutines.main {
        progress_bar.show()
         viewModel.topRated.await().observe(viewLifecycleOwner, Observer {
            progress_bar.hide()
            initRecyclerView(it.toTopItem())
        })
    }

    private fun initRecyclerView(quoteItem: List<TopItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }


    private fun List<TopRated>.toTopItem() : List<TopItem>{
        return this.map {
            TopItem(it)
        }
    }


}
