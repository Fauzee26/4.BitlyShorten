package fauzi.hilmy.bitlyshorten;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fauzi.hilmy.bitlyshorten.api.ApiClient;
import fauzi.hilmy.bitlyshorten.api.ApiInterface;
import fauzi.hilmy.bitlyshorten.model.ResponseUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edtLink)
    TextInputEditText edtLink;
    @BindView(R.id.btnShort)
    Button btnShort;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.txtBitly)
    TextView txtBitly;
    @BindView(R.id.btnCopy)
    Button btnCopy;
    @BindView(R.id.btnShare)
    Button btnShare;
    String url, bitt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        url = edtLink.getText().toString();
        bitt = txtBitly.getText().toString();
        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = edtLink.getText().toString();
                bitt = txtBitly.getText().toString();
                if (bitt.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nothing to copy", Toast.LENGTH_SHORT).show();
                } else {
                    copyText(bitt);
                }
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = edtLink.getText().toString();
                bitt = txtBitly.getText().toString();
                if (bitt.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nothing to share", Toast.LENGTH_SHORT).show();
                } else {
                    shareText(bitt);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = edtLink.getText().toString();
                bitt = txtBitly.getText().toString();
                if (url.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nothing to delete", Toast.LENGTH_SHORT).show();
                } else {
                    edtLink.setText("");
                }
            }
        });

        btnShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = edtLink.getText().toString();
                bitt = txtBitly.getText().toString();
                if (url.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nothing to create", Toast.LENGTH_SHORT).show();
                } else {
                    if (!url.startsWith("http"))
                        url = "https://" + url;
//                    String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
//                    if (!url.matches(regex)) {
//                        Toast.makeText(MainActivity.this, "Invalid URL match", Toast.LENGTH_SHORT).show();
//                        edtLink.requestFocus();

//                    Bitlyzer b = new Bitlyzer("o_6763834psq", "R_7ce47ae45dd24f03a25a43d23983d09d");
//                    b.shorten(url, new Bitlyzer.BitlyzerCallback() {
//                        @Override
//                        public void onSuccess(String shortUrl) {
//                            txtBitly.setText(shortUrl);
//                            Toast.makeText(MainActivity.this, "Shorten URL Created", Toast.LENGTH_SHORT).show();
//                            Log.d("Shorten", shortUrl);
//                        }
//
//                        @Override
//                        public void onError(String reason) {
//                            Toast.makeText(MainActivity.this, "Oops, There is an error", Toast.LENGTH_SHORT).show();
//                            Log.wtf("Error!!", reason);
//                        }
//                    });

                    ApiInterface apiInterface = ApiClient.getInstance();
                    Call<ResponseUrl> call = apiInterface.getUrl(url);
                    call.enqueue(new Callback<ResponseUrl>() {
                        @Override
                        public void onResponse(Call<ResponseUrl> call, Response<ResponseUrl> response) {
                            if (response.body().getStatusTxt().equals("OK")) {
                                txtBitly.setText(response.body().getData().getUrl());
                                Toast.makeText(MainActivity.this, "Shorten URL Created", Toast.LENGTH_SHORT).show();
                                Log.d("Shorten", response.body().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseUrl> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Oops, There is an error", Toast.LENGTH_SHORT).show();
                            Log.wtf("Error!!", t.getMessage());
                        }
                    });
                }
            }
        });

    }

    public void shareText(String bitkuu) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Your Bitly!");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, bitkuu);
        startActivity(Intent.createChooser(intent, "Choose Sharing Method"));
    }

    public void copyText(String bit) {
        int sdk = android.os.Build.VERSION.SDK_INT;

        if (bit.length() != 0) {
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {

                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(bit);
                Toast.makeText(getApplicationContext(), "Text Copied to Clipboard", Toast.LENGTH_SHORT).show();

            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Clip", bit);
                Toast.makeText(getApplicationContext(), "Text Copied to Clipboard", Toast.LENGTH_SHORT).show();
                clipboard.setPrimaryClip(clip);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Nothing to Copy", Toast.LENGTH_SHORT).show();

        }
    }
}
