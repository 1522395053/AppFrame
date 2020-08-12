package com.ssyh.baseframe.net;

import com.google.gson.JsonSyntaxException;
import com.ssyh.baseframe.BuildConfig;
import com.ssyh.baseframe.bean.EventBusConstant;
import com.ssyh.baseframe.bean.EventBusMsg;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NetDataCallback<T> implements Callback<RspModel<T>> {
    public final static int CODE_SUCCESS = 200;//成功
    public final static int CODE_FAIL = 400;//请求无效，没有进入后台服务
    public final static int CODE_UNAUTHORIZED = 401;//未认证（签名错误）
    public final static int CODE_NOT_FOUND = 404;//接口不存在
    public final static int CODE_METHOD_NOT_ALLOW = 405;//请求方法不允许
    public final static int CODE_METHOD_REQUEST_TOO_LARGE = 413;//请求Entity过大
    public final static int CODE_INTERNAL_SERVER_ERROR = 500;//服务器内部错误
    public final static int CODE_INTERNAL_SERVER_RESTARTING = 502;//服务器重启中

    /////
    public final static int CODE_NET_EXCEPTION_SOCKETTIMEOUT = -100;//连接超时
    public final static int CODE_NET_EXCEPTION_UNKNOWNHOST = -101;//没有连接网络
    public final static int CODE_NET_EXCEPTION_CONNECT = -102;//网络连接错误
    public final static int CODE_NET_EXCEPTION_IO = -103;//网络异常



    @Override
    public void onResponse(Call<RspModel<T>> call, Response<RspModel<T>> response) {
//        try {
//            String string = response.raw().body().string(); //已使用converter转换，不能再这样获取
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
            if (CODE_SUCCESS == response.code()) {//200是服务器有合理响应
            RspModel<T> rspModel = response.body();
            if (rspModel != null) {
                if (rspModel.getCode() == RspModel.SERVER_CODE_SUCCESS) {
                    handleCommonEvent(rspModel);
                    //返回成功结果
                    success(rspModel.getData());
                } else { //返回的结果码为错误码
                    fail(rspModel.getCode(),rspModel.getMsg());
                    fail2(rspModel.getCode(),rspModel.getMsg());
                }
            } else {
                //rspModel == null
                fail(-3,"rspModel:null");
                fail2(-3,"rspModel:null");
            }
        } else {//失败响应
            handleNetError(response.code());
        }
        complete();
    }

    protected void handleNetError(int netErrorCode){
        String msg = "请求失败";
        if (BuildConfig.DEBUG){
            switch (netErrorCode){
                case CODE_FAIL:
                    msg = "请求无效";
                    break;
                case CODE_UNAUTHORIZED:
                    msg = "未认证";
                    break;
                case CODE_NOT_FOUND:
                    msg = "接口不存在";
                    break;
                case CODE_METHOD_NOT_ALLOW:
                    msg = "请求方法不允许";
                    break;
                case CODE_METHOD_REQUEST_TOO_LARGE:
                    msg = "请求数据过大(Request Entity Too Large)";
                    break;
                case CODE_INTERNAL_SERVER_ERROR:
                    msg = "服务器内部错误";
                    break;
                case CODE_INTERNAL_SERVER_RESTARTING:
                    msg = "服务器重启中";
                    break;

            }
        }
        fail(-1,msg);
        fail2(-1,msg);
    }


    @Override
    public void onFailure(Call<RspModel<T>> call, Throwable t) {
        String msg = "请求数据异常";//
        int errorCode = -2;//
        if (t instanceof SocketTimeoutException) {//超时
            msg = "请求超时，请重新加载";
            errorCode = CODE_NET_EXCEPTION_SOCKETTIMEOUT;
        } else if (t instanceof UnknownHostException) { //无网络
            msg = "无网络连接，请检查网络设置";
            errorCode = CODE_NET_EXCEPTION_UNKNOWNHOST;
        }else if (t instanceof ConnectException) { //网络连接错误
            msg = "网络连接错误";
            errorCode = CODE_NET_EXCEPTION_CONNECT;
        }else if (t instanceof IOException){//网络异常。IO
            msg = "网络异常";
        }

        if (BuildConfig.DEBUG){
            if (t instanceof UnknownError) { //未找到主机
                msg = "未找到主机";
            }  else if (t instanceof JsonSyntaxException) { //Retrofit callback定义泛型和服务器返回的数据结构不一致
                msg = "解析数据出错";
            }
        }
        fail(errorCode,msg);
        fail2(errorCode,msg);
        complete();
    }

    protected abstract void success(T data);
    protected void fail(int code, String errMsg){
        //ShowToastUtils.showToast(errMsg);
    }

    private void fail2(int code, String errMsg){
        boolean isNoNet = false;
        if (code == CODE_NET_EXCEPTION_SOCKETTIMEOUT ||
                code == CODE_NET_EXCEPTION_UNKNOWNHOST||
                code == CODE_NET_EXCEPTION_CONNECT ||
                code == CODE_NET_EXCEPTION_IO){
            isNoNet = true;
        }else if(code == RspModel.SERVER_CODE_NOT_LOGIN ||
                code == RspModel.SERVER_CODE_IN_OTHER_LOGIN){
            //ShowToastUtils.showToast("登录已过期");
            EventBus.getDefault().post(new EventBusMsg(EventBusConstant.EVENT_CODE_LOGIN_OUT_BY_KICK));
            return;
        }
        error(errMsg,isNoNet);
    }

    protected void error(String errMsg, boolean isNoNet){

    }
    protected void complete(){
    }

    private void handleCommonEvent(RspModel<T> rspModel) {
        if (null != rspModel &&rspModel.refresh_task_buoy){
            EventBus.getDefault().post(new EventBusMsg(EventBusConstant.EVENT_CODE_UDPATE_RED_ENVELOPE_WINDOW,true));
        }
    }
}
