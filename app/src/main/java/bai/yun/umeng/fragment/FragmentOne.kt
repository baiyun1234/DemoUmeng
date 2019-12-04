package bai.yun.umeng.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bai.yun.umeng.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentOne : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listenerOne: OnFragmentInteractionListenerOne? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fragment_one, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listenerOne?.onFragmentInteractionOne(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListenerOne) {
            listenerOne = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListenerOne")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerOne = null
    }

    interface OnFragmentInteractionListenerOne {
        fun onFragmentInteractionOne(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentOne().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
