package com.example.myapplicationagian.artDetailScreen

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.example.myapplicationagian.databinding.FragmentArtDetailBinding


class ArtDetailFragment : Fragment() {

    private var currentAnimator: Animator? = null

    private var shortAnimationDuration: Int = 0

    private lateinit var binding: FragmentArtDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = FragmentArtDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val artwork = ArtDetailFragmentArgs.fromBundle(requireArguments()).selectedArtwork
        binding.artworkDetail = artwork

//        val bitmap = (binding.detailIImgView.getDrawable() as BitmapDrawable).bitmap


        return binding.root
        val imgView: View = binding.detailIImgView
        imgView.setOnClickListener({
            zoomImageFromThumb(imgView, binding.expandedImage.id)
        })
        shortAnimationDuration= resources.getInteger(android.R.integer.config_shortAnimTime)

//Palette

    }
    private fun zoomImageFromThumb(thumbView: View, imageResId: Int ) {
        currentAnimator?.cancel()

        val expandedImageView : ImageView = binding.expandedImage
        expandedImageView.setImageResource(imageResId)


        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        thumbView.getGlobalVisibleRect(startBoundsInt)
        binding.container.getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        val startScale: Float
        if((finalBounds.width()/ finalBounds.height() > startBounds.width() /startBounds.height())) {
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale*finalBounds.height()
            val deltaWidth: Float = (startWidth - startBounds.width()) /2
            startBounds.left-= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.width()
            val deltaHeight: Float = (startHeight-startBounds.height()) /2f
            startBounds.top -=deltaHeight.toInt()
            startBounds.bottom+=deltaHeight.toInt()
        }
        thumbView.alpha = 0f
       binding.expandedImage.visibility = View.VISIBLE
        binding.expandedImage.pivotX=0f
        binding.expandedImage.pivotY=0f

        currentAnimator = AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(
                binding.expandedImage,
                View.X,
                startBounds.left,
                finalBounds.left)
            ).apply {
                with(ObjectAnimator.ofFloat(binding.expandedImage, View.Y, startBounds.top, finalBounds.top))
                with(ObjectAnimator.ofFloat(binding.expandedImage, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(binding.expandedImage, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }
                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }

            } )
       start()

    }

        binding.expandedImage.setOnClickListener {
            currentAnimator?.cancel()
            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(binding.expandedImage, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(binding.expandedImage, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(binding.expandedImage, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(binding.expandedImage, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }
                })
                start()
            }
        }
        }


}


























