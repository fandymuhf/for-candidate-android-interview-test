
package com.tokopedia.testproject.problems.androidView.slidingImagePuzzle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tokopedia.testproject.R
import com.tokopedia.testproject.fromHtml
import com.tokopedia.testproject.loadFile
import kotlinx.android.synthetic.main.activity_problem_simulation.*

class SlidingImagePuzzleCaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // for example only. You may change this to any image url.
        var imageUrl = "https://2.bp.blogspot.com/-XayoouLVgUs/WhfQ-X4Du2I/AAAAAAAABec/7A4zWqiQ1cs6r1g-wnYz9bklg6A8Z3oGwCEwYBhgL/s1600/meme%2Bkucing%2B8.jpg"


        setContentView(R.layout.activity_problem_simulation)
        webView.loadFile("sliding_image.html")
        btn_simulate.setOnClickListener {
            val rand: Int = ((Math.random() * 3) + 1).toInt();
            if(rand==1)imageUrl = "http://cms.centroone.com/contents/News/22360/22360_original.jpg"
            else if(rand==2)imageUrl = "https://pics.me.me/kucing-pala-belbie-pucing-woi-bukan-kucing-%F0%9F%98%86-1268564.png"
            else imageUrl = "https://2.bp.blogspot.com/-XayoouLVgUs/WhfQ-X4Du2I/AAAAAAAABec/7A4zWqiQ1cs6r1g-wnYz9bklg6A8Z3oGwCEwYBhgL/s1600/meme%2Bkucing%2B8.jpg"

            startActivity(SlidingImageGameActivity.getIntent(this, imageUrl))
        }
    }

}
