package com.example.mymoviedatabase.ui.UpcomingMovies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.data.db.entities.Upcoming
import com.example.mymoviedatabase.util.Coroutines
import com.example.mymoviedatabase.util.hide
import com.example.mymoviedatabase.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.upcoming_movies_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class UpcomingMoviesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: UpcomingMoviesViewModelFactory by instance()

    private lateinit var viewModel: UpcomingMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.upcoming_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(UpcomingMoviesViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {

        progress_bar_up.show()
        viewModel.upcoming.await().observe(viewLifecycleOwner, Observer {  progress_bar_up.hide()
            initRecyclerView(it.toUpcomingItem()) })
    }

    private fun initRecyclerView(upcomingItem: List<UpcomingItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(upcomingItem)
        }

        recycler_view_up.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }


    private fun List<Upcoming>.toUpcomingItem() : List<UpcomingItem>{
        return this.map {
            UpcomingItem(it)
        }
    }


}
