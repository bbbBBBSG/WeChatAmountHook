package com.dahai.demo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ImageHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        // 当前启动应用的包名
        if (lpparam.packageName.equals("com.tencent.mm")) {

            Class clazz = lpparam.classLoader.loadClass("com.tencent.mm.plugin.mall.ui.MallIndexBaseUI");
            XposedHelpers.findAndHookMethod(clazz, "bJ", new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Object thisObject = param.thisObject;
                    try {
                        Class<?> superclass = thisObject.getClass().getSuperclass();

                        Field pjt = superclass.getDeclaredField("pjt");
                        pjt.setAccessible(true);
                        TextView textView = (TextView) pjt.get(thisObject);
                        SharedPreferences preferences = textView.getContext().getSharedPreferences("DH_HOOK", Context.MODE_PRIVATE);
                        String amount = preferences.getString("Amount", "");
                        Log.e("HHH", "afterHookedMethod: " + amount );
                        if (!TextUtils.isEmpty(amount)) {
                            textView.setText(String.format("¥%s",amount));
                        }
                    } catch (Exception e) {
                        Log.e("HHH", "afterHookedMethod: ", e );
                    }
                }
            });

            Class uploadUI = lpparam.classLoader.loadClass("com.tencent.mm.plugin.sns.ui.SnsUploadUI");

            XposedHelpers.findAndHookMethod(uploadUI, "onPause", new XC_MethodHook() {

                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.e("HHH", "beforeHookedMethod: " );
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    final Object thisObject = param.thisObject;
                    try {
                        Field svr = thisObject.getClass().getDeclaredField("svr");
                        svr.setAccessible(true);
                        EditText editText = (EditText) svr.get(thisObject);
                        String trim = editText.getText().toString().trim();
                        if (!TextUtils.isEmpty(trim)) {
                            if (trim.startsWith("￥")) {
                                String amount = trim.replace("￥", "");
                                SharedPreferences preferences = editText.getContext().getSharedPreferences("DH_HOOK", Context.MODE_PRIVATE);
                                preferences.edit().putString("Amount",amount).apply();
                                Toast.makeText(editText.getContext(), "神秘代码完成", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        Log.e("HHH", "afterHookedMethod: ", e );
                    }
                }
            });
        }
    }

}
