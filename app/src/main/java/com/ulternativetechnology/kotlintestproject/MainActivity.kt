package com.ulternativetechnology.kotlintestproject

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PageAdapter(supportFragmentManager).apply {
            addFragment(NaverWebViewFragment(), "네이버")
            addFragment(DaumWebViewFragment(), "다음")
            addFragment(StackOverflowWebViewFragment(), "스택오버플로우")
        }
        viewpager.adapter = adapter
        tablayout.setupWithViewPager(viewpager)

        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.text == "스택오버플로우" || tab.text == "다음") {
                    /* 다음 or 스택오버플로우 탭을 클릭하면 텍스트뷰, 이미지뷰를 애니메이션 효과를 입혀서 Fade out 시킨다 */
                    val fadeOutAnim = AnimationUtils.loadAnimation(this@MainActivity, R.anim.fade_out)
                    medium_imageview.startAnimation(fadeOutAnim)
                    top_textview.startAnimation(fadeOutAnim)

                    // 텍스트뷰, 이미지뷰를 GONE으로 돌리고 탭 레이아웃의 체인을 부모 레이아웃의 top에 연결한다
                    medium_imageview.visibility = GONE
                    top_textview.visibility = GONE

                    val constraintLayout: ConstraintLayout = parent_layout
                    ConstraintSet().apply {
                        clone(constraintLayout)
                        connect(
                            R.id.tablayout,                 // startId : 어떤 뷰의 체인을 바꿀 것인가?
                            ConstraintSet.TOP,              // startSide : startId 뷰의 어디를 연결할 것인가?
                            R.id.top_textview,              // endId : 어디에 체인을 걸 것인가?
                            ConstraintSet.BOTTOM,           // endSide : endId 뷰의 어느 위치에 startId 뷰를 연결할 것인가?
                            0                       // 제한할 여백 (양수여야 함)
                        )
                        applyTo(constraintLayout)
                    }

                    /* ConstraintSet.applyTo() 호출 시 애니메이션 적용 */
                    val trans = ChangeBounds()
                    trans.interpolator = AccelerateInterpolator()
                    TransitionManager.beginDelayedTransition(constraintLayout, trans)
                } else {
                    /* 네이버 탭을 클릭하면 텍스트뷰, 이미지뷰가 보이고 탭 레이아웃이 원래 위치로 되돌아간다 */
                    val fadeInAnim = AnimationUtils.loadAnimation(this@MainActivity, R.anim.fade_in)
                    medium_imageview.startAnimation(fadeInAnim)
                    top_textview.startAnimation(fadeInAnim)

                    // 스택오버플로우 외의 다른 탭을 클릭하면 텍스트뷰, 이미지를 VISIBLE로 바꾸고 체인을 원래대로 바꾼다
                    medium_imageview.visibility = VISIBLE
                    top_textview.visibility = VISIBLE

                    val constraintLayout: ConstraintLayout = parent_layout
                    ConstraintSet().apply {
                        clone(constraintLayout)
                        connect(
                            R.id.tablayout,
                            ConstraintSet.TOP,
                            R.id.medium_imageview,
                            ConstraintSet.BOTTOM,
                            0
                        )
                        applyTo(constraintLayout)
                    }

                    /* ConstraintSet.applyTo() 호출 시 애니메이션 적용 */
                    val trans = ChangeBounds()
                    trans.interpolator = AccelerateInterpolator()
                    TransitionManager.beginDelayedTransition(constraintLayout, trans)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //
            }
        })
    }

}