package com.allinonesocial.allsocialactivitie;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragmentinsta.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragmentinsta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentinsta extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    WebView web;
    private Context context;
    String url="https://www.instagram.com";
    ProgressBar progressBar;
    String u;
    private InterstitialAd interstitialAd;
    static int i=-1;
   // FloatingActionButton f1,f3;
    private ValueCallback mFilePathCallback;
    private final static int FILECHOOSER_RESULTCODE = 1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SwipeRefreshLayout swipe;
  //  private AdView mAdView;
    private OnFragmentInteractionListener mListener;
//FloatingActionMenu f2;
    public BlankFragmentinsta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragmentinsta.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragmentinsta newInstance(String param1, String param2) {
        BlankFragmentinsta fragment = new BlankFragmentinsta();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_insta, container, false);
        web=(WebView) view.findViewById(R.id.web);
        //mAdView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);
        context = container.getContext();

        /*mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });*/
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.i("hhh","Permission is granted");

            } else {

               // Toast.makeText(getActivity(),"If you want to invite friends,permission required for sharing image",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        }
      //  f3=(FloatingActionButton)view.findViewById(R.id.menu_item1);
        //f1=(FloatingActionButton)view.findViewById(R.id.menu_item);
        //f2=(FloatingActionMenu)view.findViewById(R.id.menu);
        /*f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    // image naming and path  to include sd card  appending name you choose for file
                    //actionBar.hide();
                    Date currentTime = Calendar.getInstance().getTime();
                    // String mPath = Environment.getExternalStorageDirectory().toString() + "/Useless/" +currentTime + ".jpg";
                    f1.setVisibility(View.INVISIBLE);
                    mAdView.setVisibility(View.INVISIBLE);
                    f2.setVisibility(View.INVISIBLE);
                    f3.setVisibility(View.INVISIBLE);
                    // create bitmap screen capture
                    View v1 = getActivity().getWindow().getDecorView().getRootView();
                    v1=view.findViewById(R.id.frm);
                    v1.setDrawingCacheEnabled(true);

                    Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
                    v1.setDrawingCacheEnabled(false);

                    //File imageFile = new File(mPath);
                    File file= Environment.getExternalStorageDirectory();
                    File dir=new File(file+"/Allinone/");
                    dir.mkdir();
                    File mp=new File(dir,currentTime+".jpg");

                    FileOutputStream outputStream = new FileOutputStream(mp);
                    int quality = 100;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    f1.setVisibility(View.VISIBLE);
                    mAdView.setVisibility(View.VISIBLE);
                    f2.setVisibility(View.VISIBLE);
                    f3.setVisibility(View.VISIBLE);
                    //actionBar.show();
                    Toast.makeText(getActivity(),"Saved Successfully", Toast.LENGTH_SHORT).show();


                } catch (Throwable e) {
                    f1.setVisibility(View.VISIBLE);
                    mAdView.setVisibility(View.VISIBLE);
                    f2.setVisibility(View.VISIBLE);
                    f3.setVisibility(View.VISIBLE);
                    // Several error may come out with file handling or DOM
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"Error Occured,Please Check for permissions", Toast.LENGTH_SHORT).show();

                }
            }
        });*/
      /*  f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My app name");
                    String strShareMessage = "\nLet me recommend you this application\n\n";
                    strShareMessage = strShareMessage + "https://play.google.com/store/apps/details?id=" + "com.ragnar.useless";
                    Uri screenshotUri = Uri.parse("android.resource://com.allinonesocial.allsocialactivities/drawable/concept");
                    i.setType("image/png");
                    i.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                    i.putExtra(Intent.EXTRA_TEXT, strShareMessage);
                    startActivity(Intent.createChooser(i, "Share via"));
                } catch(Exception e) {
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                }
                try {
                   /* Intent i = new Intent(Intent.ACTION_SEND);
                 //   i.setType("text/plain");
                  //  i.putExtra(Intent.EXTRA_SUBJECT, "My app name");
                   // String strShareMessage = "\nLet me recommend you this application\n\n";
                    //strShareMessage = strShareMessage + "https://play.google.com/store/apps/details?id=com.ragnar.useless";

                  //  Uri screenshotUri = Uri.parse("android.resource://"+getContext().getPackageName()+"/"+R.drawable.ssss);



                    i.setType("image/jpeg");
                    i.putExtra(Intent.EXTRA_STREAM,bmpUri);
                   // i.putExtra(Intent.EXTRA_TEXT, strShareMessage);
                    startActivity(Intent.createChooser(i, "Share Image"));
                    Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.sec);
                    String pathofBmp = MediaStore.Images.Media.insertImage(getActivity().getApplicationContext().getContentResolver(),icon,"title", null);
                    Uri bmpUri = Uri.parse(pathofBmp);
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.allinonesocial.allsocialactivities \n All in one social media app,give it a try I loved it");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/jpeg");
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(Intent.createChooser(shareIntent, "send"));


                } catch(Exception e) {
                    //e.toString();
                    Log.i("Errrrr",e+"");
                    Toast.makeText(getActivity()," "+e,Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(this);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);
        progressBar=(ProgressBar)view.findViewById(R.id.pro);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(),R.color.colorAccent), PorterDuff.Mode.SRC_IN );
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId("ca-app-pub-9874900066695797/4132453105");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener(){
            public void onAdLoaded(){
                interstitialAd.show();
            }
        });
        web.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,              FileChooserParams fileChooserParams) {
                mFilePathCallback = filePathCallback;
                // Launch Intent for picking file
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, FILECHOOSER_RESULTCODE);
                return true;
            }
        });
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                u=url;
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                swipe.setRefreshing(false);
                interstitialAd.setAdListener(new AdListener(){
                    public void onAdLoaded(){
                        interstitialAd.show();
                    }
                });

            }

            @Override
            public void onReceivedError(final WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                view.loadUrl("file:///android_asset/sample.html");
                Toast.makeText(context,"Problem loading,Check your internet connection",Toast.LENGTH_SHORT).show();
                i=1;
            }


        });
        web.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && web.canGoBack()) {
                    web.goBack();
                    return true;
                }
                return false;
            }
        });
        web.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                WebView.HitTestResult hr = ((WebView) v).getHitTestResult();

                //Log.i(TAG, "getExtra = " + hr.getExtra() + "\t\t Type=" + hr.getType());
                if(i>0)
                {web.loadUrl(url);
                    Log.i(TAG,"YES");
                    i=-1;}
                return false;
            }
        });


        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
      web.loadUrl(url);
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {
        try{
        if (requestCode == FILECHOOSER_RESULTCODE) {
            Uri result = intent == null || resultCode != RESULT_OK ? null
                    : intent.getData();
            if (resultCode != RESULT_OK)
            {
                mFilePathCallback.onReceiveValue(null);
                return;
            }
            Uri[] resultsArray = new Uri[1];
            resultsArray[0] = result;
            mFilePathCallback.onReceiveValue(resultsArray);
        }

        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),"Not uploaded",Toast.LENGTH_SHORT).show();
        }
    }


}










