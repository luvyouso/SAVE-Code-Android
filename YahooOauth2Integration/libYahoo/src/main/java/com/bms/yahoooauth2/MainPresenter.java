package com.bms.yahoooauth2;

import android.app.Activity;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by rahulkumar on 25/02/17.
 */

public class MainPresenter implements ControllerToPresenterDataTransferInterface {

    MainActivity mainActivity;
    UseCaseController useCaseController;
    CompositeSubscription compositeSubscription = new CompositeSubscription();
    String urll, access_token, guid;
    GetData mGetData;

    public GetData getGetData() {
        return mGetData;
    }

    public void setGetData(GetData getData) {
        this.mGetData = getData;
    }

    @Inject
    public MainPresenter(Activity activity) {
        useCaseController = new UseCaseController(this, activity);
    }


    public void intializeView(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    public void getResults(Map<String, Object> objectMap, String url, String authorization, String contentType) {
        if (Utils.checkInternetConnection(mainActivity)) {
            urll = url;
            ProgressDialogg.ShowDialog(mainActivity);
            compositeSubscription.add(useCaseController.HitApi(objectMap, url, authorization, contentType));
        } else {
            Toast.makeText(mainActivity, "no internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void getProfile(Map<String, Object> objectMap, String url, String authorization) {
        if (Utils.checkInternetConnection(mainActivity)) {
            urll = url;
            ProgressDialogg.DismissDialog();
            compositeSubscription.add(useCaseController.HitApi(objectMap, url, authorization, null));
        } else {
            Toast.makeText(mainActivity, "no internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void getConnections(Map<String, Object> objectMap, String url, String authorization) {
        if (Utils.checkInternetConnection(mainActivity)) {
            urll = url;
            ProgressDialogg.DismissDialog();
            compositeSubscription.add(useCaseController.HitApi(objectMap, url, authorization, null));
        } else {
            Toast.makeText(mainActivity, "no internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void getContacts(Map<String, Object> objectMap, String url, String authorization) {
        if (Utils.checkInternetConnection(mainActivity)) {
            urll = url;
            ProgressDialogg.DismissDialog();
            compositeSubscription.add(useCaseController.HitApi(objectMap, url, authorization, null));
        } else {
            Toast.makeText(mainActivity, "no internet connection", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void transferapiResult(Object o) {
        if (o != null) {
            try {
                Observable.just(o).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io()).subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        ProgressDialogg.DismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ProgressDialogg.DismissDialog();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Object o) {
                        ProgressDialogg.DismissDialog();
                        try {

                            ObjectMapper mapper = new ObjectMapper();
                            switch (urll) {
                                case Constants.OAUTH_TOKEN_URL:
                                    Token object = mapper.convertValue(o, Token.class);
                                    if (object.getAccessToken() != null) {
                                        System.out.println("access token" + object.getAccessToken());
                                        System.out.println("refresh token" + object.getRefreshToken());
                                        System.out.println("token type" + object.getTokenType());
                                        System.out.println("id" + object.getXoauthYahooGuid());
                                        Map<String, Object> params = new HashMap<String, Object>();
                                        params.put("url", Constants.SOCIAL_URL);
                                        params.put("yahoo_id", object.getXoauthYahooGuid());
                                        access_token = "Bearer" + " " + object.getAccessToken();
                                        getProfile(params, Constants.PROFILE_URL, access_token);
                                    }
                                    break;
                                case Constants.PROFILE_URL:
                                    Profile profile = mapper.convertValue(o, Profile.class);
                                    mGetData.getData(profile);
                                    if (profile.getProfile() != null && profile.getProfile().getGivenName() != null) {
                                        System.out.println("name is" + profile.getProfile().getGivenName());
                                        Map<String, Object> params = new HashMap<String, Object>();
                                        guid = profile.getProfile().getGuid();
                                        params.put("url", Constants.SOCIAL_URL);
                                        params.put("yahoo_id", guid);
                                        getContacts(params, Constants.CONTACTS_URL, access_token);
                                    }
                                    break;
                                case Constants.CONTACTS_URL:
                                    Gson gson = new Gson();
                                    String d = gson.toJson(o);
                                    JSONObject object1 = new JSONObject(d);
                                    JSONObject contacts = object1.optJSONObject("contacts");
                                    JSONArray contact = contacts.optJSONArray("contact");
                                    for (int i = 0; i < contact.length(); i++) {
                                        int id = contact.getJSONObject(i).getInt("id");
                                        System.out.println("id is" + id);
                                    }
                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("url", Constants.SOCIAL_URL);
                                    params.put("yahoo_id", guid);
                                    getContacts(params, Constants.CONTACTS_URL, access_token);
                                    getConnections(params, Constants.CONNECTIONS_URL, access_token);
                                    break;
                                case Constants.CONNECTIONS_URL:
                                    Gson gsonn = new Gson();
                                    String dd = gsonn.toJson(o);
                                    JSONObject object11 = new JSONObject(dd);
                                    System.out.println("objectt iss :" + object11);
                                    break;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public void transferError(Throwable e) {
        if (e != null && e.getMessage() != null && !e.getMessage().equalsIgnoreCase("")) {
            Observable.just(e).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Throwable>() {
                @Override
                public void onCompleted() {
                    ProgressDialogg.DismissDialog();
                }

                @Override
                public void onError(Throwable e) {
                    ProgressDialogg.DismissDialog();
                    Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(Throwable throwable) {
                    ProgressDialogg.DismissDialog();
                    Toast.makeText(mainActivity, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void Destroy() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
            compositeSubscription.clear();
        }
        intializeView(null);
    }
}

