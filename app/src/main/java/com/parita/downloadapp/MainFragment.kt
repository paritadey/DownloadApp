package com.parita.downloadapp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.parita.downloadapp.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.content_main.view.*

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var transitionListner : MotionLayout.TransitionListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        transitionListner = object : MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                Handler().postDelayed(object : Runnable{
                    override fun run() {
                        binding.downloadButton.motionLayout.button.text = ""
                        binding.downloadButton.motionLayout.transitionToStart()//Using this will create a smooth transition to the initial state.
                        //binding.downloadButton.motionLayout.jumpToState(R.id.start)//Using this will create a direct jump to the initial state with out the animation.
                    }
                }
                ,1000)
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        }
        binding.downloadButton.motionLayout.setTransitionListener(transitionListner)
    }
}