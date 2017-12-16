package com.derekyu624gmail.themoreyouknow


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 */
class SpaceFragment : Fragment() {
    private lateinit var textView: TextView
    private var databaseAccess: DatabaseAccess? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_space, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.textView = view!!.findViewById(R.id.factArea)

        databaseAccess = DatabaseAccess.getInstance(activity)
        databaseAccess?.open()

        var button: Button = view!!.findViewById(R.id.button)
        button.setOnClickListener(View.OnClickListener { onButtonTap(view!!) })
    }

    fun onButtonTap(v: View) {
        this.textView.setText(null)
        val fact: String = databaseAccess!!.getFact("space")
        this.textView.setText(fact)

    }

}// Required empty public constructor
