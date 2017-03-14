package com.ywj.live.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ywj.live.R;
import com.ywj.live.adapter.Home_Live_Two_Im_Adapter;
import com.ywj.live.adapter.Home_Live_Two_User_Adapter;
import com.ywj.live.adapter.ListAdapter;
import com.ywj.live.adapter.decoration.DividerItemDecoration;
import com.ywj.live.entity.TempBean;
import com.ywj.live.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import tyrantgit.widget.HeartLayout;

/**
 * Created by yj on 2017/3/12.
 */

public class Fragment_Two extends Fragment {

    @BindView(R.id.live_tow_llt)
    LinearLayout liveTowLlt;
    @BindView(R.id.input)
    LinearLayout input;
    @BindView(R.id.love_im_rll)
    RelativeLayout loveImRll;
    @BindView(R.id.two_fragment_hear)
    HeartLayout twoFragmentHear;
    @BindView(R.id.msg_et)
    EditText msgEt;
    @BindView(R.id.send_btn)
    Button sendBtn;
    /**
     * 临时数据
     */
    private String hard_url = "https://tower.im/assets/default_avatars/jokul.jpg";

    @BindView(R.id.live_tow_im)
    TextView live_tow_im_img;

    @BindView(R.id.live_two_love)
    ImageView live_two_love;

    @BindView(R.id.live_tow_gift)
    TextView live_tow_gift;

    @BindView(R.id.live_two_head)
    SimpleDraweeView live_two_head;

    @BindView(R.id.live_tow_rl)
    RecyclerView live_tow_rl;

    @BindView(R.id.live_tow_im_lv)
    ListView live_tow_im_rl;

    private Handler handler = new Handler();
    private Home_Live_Two_User_Adapter mTwo_user_adapter;
    //private Home_Live_Two_Im_Adapter mTwo_im_adapter;

    private List<String> mDatas;
    private ListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.two_fragment, container, false);
        ButterKnife.bind(this, view);
        initData();
        setListData();
        getLove();
        setChat();
        setSend();
        sendGift();
        //sendMsg();
        return view;
    }

    private void initData() {
        live_two_head.setImageURI(hard_url);
        mTwo_user_adapter = new Home_Live_Two_User_Adapter(getContext(), getHeadData());

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        live_tow_rl.setLayoutManager(linearLayoutManager);
        live_tow_rl.setItemAnimator(new DefaultItemAnimator());
        live_tow_rl.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL_LIST));
        live_tow_rl.setAdapter(mTwo_user_adapter);


//        mTwo_im_adapter = new Home_Live_Two_Im_Adapter(getContext(), getHeadData());
//        live_tow_im_rl.setLayoutManager(new LinearLayoutManager(getContext()));
//        live_tow_im_rl.setItemAnimator(new DefaultItemAnimator());
//        live_tow_im_rl.addItemDecoration(new DividerItemDecoration(getContext(),
//                DividerItemDecoration.HORIZONTAL_LIST));
//        live_tow_im_rl.setAdapter(mTwo_im_adapter);

    }
    private void sendGift() {
        live_tow_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog(v);
            }
        });
    }

    public void showBottomDialog(View v) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        BottomGiftDialogFragment editNameDialog = new BottomGiftDialogFragment();
        editNameDialog.show(fm, "fragment_bottom_dialog");
    }

    private void setListData() {
        mDatas = new ArrayList<>();
        listAdapter = new ListAdapter(getContext(), getListData());
        live_tow_im_rl.setAdapter(listAdapter);
        live_tow_im_rl.setSelection(live_tow_im_rl.getBottom());
        live_tow_im_rl.setOnKeyListener(backListener);//返回键监听
    }

    public List<String> getListData() {
        for (int i = 0; i < 20; i++) {
            mDatas.add("107 最牛X班级 " + i);
        }
        return mDatas;
    }

    private void setSend() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }

    private void setChat() {
        live_tow_im_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setVisibility(View.VISIBLE);
                loveImRll.setVisibility(View.INVISIBLE);
                twoFragmentHear.setVisibility(View.GONE);
                msgEt.requestFocus();
                openInput();
            }
        });
    }

    private void openInput() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void send() {
        String msg = msgEt.getText().toString().trim();
        if (!TextUtils.isEmpty(msg)) {
            sendMsgToList(msg);//发送消息
            msgEt.setText("");
        } else {
            ToastUtils.show(getContext(), "不能发送空消息");
        }
    }

    private void sendMsgToList(String msg) {
        mDatas.add("YJ " + msg);
        listAdapter = new ListAdapter(getContext(), mDatas);
        listAdapter.notifyDataSetChanged();
        live_tow_im_rl.setSelection(live_tow_im_rl.getBottom());
    }

    private View.OnKeyListener backListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.getAction() == KeyEvent.ACTION_DOWN) {
                input.setVisibility(View.GONE);
                loveImRll.setVisibility(View.VISIBLE);
                twoFragmentHear.setVisibility(View.VISIBLE);
                onKeyUp(keyCode, event);
                return true;
            }
            return false;
        }
    };

    public boolean onKeyUp(int keyCode, KeyEvent keyEvent) {
        if (keyCode == keyEvent.KEYCODE_BACK
                && keyEvent.getAction() == KeyEvent.ACTION_UP) {
            // TODO: 2017/3/13 0013
            closeInput();

            return true;
        }
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            // TODO: 2017/3/13 0013
            return true;
        }
        return false;
    }

    /**
     * 飘心效果
     */
    private void getLove() {
        twoFragmentHear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoFragmentHear.addHeart(Color.GRAY);
                twoFragmentHear.addHeart(Color.BLUE);
                twoFragmentHear.addHeart(Color.CYAN);
                twoFragmentHear.addHeart(Color.RED);
                twoFragmentHear.addHeart(Color.GRAY);
            }
        });


        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        twoFragmentHear.addHeart(Color.GRAY);
                        twoFragmentHear.addHeart(Color.BLUE);
                        twoFragmentHear.addHeart(Color.CYAN);
                        twoFragmentHear.addHeart(Color.RED);
                        twoFragmentHear.addHeart(Color.GRAY);
                    }
                });
            }
        };
        timer.schedule(task, 500, 500);

    }


    private List<TempBean> getHeadData() {

        List<TempBean> list = new ArrayList<>();
        TempBean tempBean = new TempBean();
        for (int i = 0; i < 100; i++) {
            tempBean.setUrl("http://bj.c.tedu.cn/zt/tg/images/cyy_03.jpg");
            //tempBean.setMessage("We are family ");
            list.add(i, tempBean);
        }
        return list;
    }

    private void closeInput() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
        if (isOpen) {
            input.clearFocus();
            input.setVisibility(View.GONE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        } else {

        }
    }

}
