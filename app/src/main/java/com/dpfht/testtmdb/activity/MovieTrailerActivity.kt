package com.dpfht.testtmdb.activity

import android.os.Bundle
import android.widget.Toast
import com.dpfht.testtmdb.PlayerConfig
import com.dpfht.testtmdb.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_movie_trailer.*
import java.util.*

class MovieTrailerActivity : YouTubeBaseActivity() {

    companion object {
        const val KEY_EXTRA_MOVIE_ID = "keyExtraMovieId"
    }

    private val viewModel = MovieTrailerViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_trailer)

        if (intent.hasExtra(KEY_EXTRA_MOVIE_ID)) {
            val movieId = intent.getIntExtra(KEY_EXTRA_MOVIE_ID, -1)

            if (movieId != -1) {
                viewModel.doGetMovieTrailers(movieId, {
                    playerYoutube.initialize(
                        PlayerConfig.API_KEY,
                        object : YouTubePlayer.OnInitializedListener {

                            override fun onInitializationSuccess(
                                p0: YouTubePlayer.Provider?,
                                youtubePlayer: YouTubePlayer?,
                                p2: Boolean
                            ) {

                                var keyVideo = ""
                                for (trailer in viewModel.trailers) {
                                    if (trailer.site?.toLowerCase(Locale.ROOT)?.trim() == "youtube") {
                                        keyVideo = trailer.key ?: ""
                                        break
                                    }
                                }

                                if (keyVideo.isNotEmpty()) {
                                    youtubePlayer?.loadVideo(keyVideo)
                                    youtubePlayer?.play()
                                }
                            }

                            override fun onInitializationFailure(
                                p0: YouTubePlayer.Provider?,
                                p1: YouTubeInitializationResult?
                            ) {
                                Toast.makeText(
                                    this@MovieTrailerActivity,
                                    "error loading youtube video",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                }, {
                    Toast.makeText(
                        this@MovieTrailerActivity,
                        "failed to load movie trailer",
                        Toast.LENGTH_SHORT
                    ).show()
                })
            }
        }
    }
}