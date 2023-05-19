package com.example.implementasifragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment


class OptionDialogFragment : DialogFragment() {
    private lateinit var btnChoose : Button
    private lateinit var btnClose:  Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rgsf: RadioButton
    private lateinit var rgjm: RadioButton
    private lateinit var rglvg: RadioButton
    private lateinit var rgdm: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rgOptions = view.findViewById(R.id.rg_options)
        rgdm = view.findViewById(R.id.rb_mou)
        rgjm = view.findViewById(R.id.rb_moyes)
        rgsf = view.findViewById(R.id.rb_saf)
        rglvg = view.findViewById(R.id.rb_lvg)

        btnChoose.setOnClickListener{
            val checkedRadioButtonId = rgOptions.checkedRadioButtonId
            if(checkedRadioButtonId != -1 ) {
                var coach: String? = when (checkedRadioButtonId) {
                    R.id.rb_mou -> rgdm.text.toString().trim()
                    R.id.rb_moyes -> rgjm.text.toString().trim()
                    R.id.rb_saf -> rgsf.text.toString().trim()
                    R.id.rb_lvg -> rglvg.text.toString().trim()
                    else -> null
                }
                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()

            } else {
                Toast.makeText(requireActivity(),"Silahkan pilih Radio Button",Toast.LENGTH_SHORT).show()
            }
        }

        btnClose.setOnClickListener{
            dialog?.cancel()
        }
    }

    override fun onAttach(context: Context){
        super.onAttach(context)

        val fragment = parentFragment

        if(fragment is DetailCategoryFragment){
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        optionDialogListener = null
    }

    interface OnOptionDialogListener{
        fun onOptionChosen(text: String?)
    }

}