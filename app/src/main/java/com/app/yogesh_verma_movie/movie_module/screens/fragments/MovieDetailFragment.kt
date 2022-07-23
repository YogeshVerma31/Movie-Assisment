package com.app.yogesh_verma_movie.movie_module.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.app.yogesh_verma_movie.BuildConfig
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.base.BaseFragment
import com.app.yogesh_verma_movie.constants.BaseAppConstants
import com.app.yogesh_verma_movie.databinding.FragmentMovieBinding
import com.app.yogesh_verma_movie.databinding.FragmentMovieDetailBinding
import com.app.yogesh_verma_movie.model.MovieModel
import com.app.yogesh_verma_movie.model.Results
import com.app.yogesh_verma_movie.movie_module.screens.activity.MainActivity
import com.app.yogesh_verma_movie.movie_module.viewmodel.MovieViewModel

class MovieDetailFragment : BaseFragment() {

    companion object {
        var TAG = BaseFragment::class.java.simpleName

        fun getInstance():MovieDetailFragment{
            return MovieDetailFragment()
        }

        fun getBundle(resultsModel: Results):Bundle{
            return Bundle().apply {
                putSerializable(BaseAppConstants.KEY_RESULTS_MODEL,resultsModel)
            }
        }
    }

    private var _viewBinder : FragmentMovieDetailBinding? = null

    private lateinit var movieViewModel:MovieViewModel


    private val resultsModel:Results?
        get(){
        return arguments?.getSerializable(BaseAppConstants.KEY_RESULTS_MODEL)as Results?
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinder = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return _viewBinder?.root
    }




    override fun initViewModels() {
        movieViewModel = getViewModel(fragment = this,viewModel = MovieViewModel(activity as BaseActivity),className = MovieViewModel::class.java)
        movieViewModel.getMovieDetails(true,BuildConfig.API_KEY,movieId = resultsModel!!.id)
    }

    override fun onViewClick(view: View?) {

    }

    override fun initView(view: View) {

    }

    override fun setListeners() {

    }

    override fun setObservers() {
        movieViewModel.movieDetailsListLiveData.observe(this, Observer {
            showToast(it.title)
        })
    }

    fun showMovieDetailFragment(resultsModel: Results){
    }
}