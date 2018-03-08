package xyz.robertsen.volleyexample;


import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends DialogFragment implements View.OnClickListener{

    private Drawable image;
    private ImageView imageView;
    private Button closeButton;

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_image, container, false);
        imageView = v.findViewById(R.id.imageView_imageFragment);
        closeButton = v.findViewById(R.id.button_imageFragment_close);
        closeButton.setOnClickListener(this);
        imageView.setImageDrawable(image);
        return v;
    }

    @Override
    public void onClick(View view) {
        this.dismiss();
    }

    /**
     * Factory metode for ImageFragment
     * @param image
     * @return
     */
    public static ImageFragment newInstance(Drawable image) {
        ImageFragment fragment = new ImageFragment();
        fragment.image = image;
        return fragment;
    }
}
