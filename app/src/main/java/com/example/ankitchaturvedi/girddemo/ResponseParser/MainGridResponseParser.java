package com.example.ankitchaturvedi.girddemo.ResponseParser;

import com.example.ankitchaturvedi.girddemo.objects.GetImageResults;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Ankit.Chaturvedi on 01-Apr-17.
 */

public class MainGridResponseParser {


    @SerializedName("page")
    public int page;

    public List<GetImageResults> results;
}
