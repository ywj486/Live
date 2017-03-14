package com.ywj.live.wight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class EditTextPreIme extends EditText {
    public EditTextPreIme(Context context) {
        super(context);
    }

    public EditTextPreIme(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextPreIme(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    public void setCurrentDialog(RenameFolderDialogS4 dialog){
//        mDialog = dialog;
//    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //when the softinput display
            //处理事件
        }
        return super.dispatchKeyEventPreIme(event);
    }

}
