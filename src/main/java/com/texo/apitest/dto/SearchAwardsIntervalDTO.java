package com.texo.apitest.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchAwardsIntervalDTO {

    private List<AwardsDTO> min = new ArrayList<>();

    private List<AwardsDTO> max = new ArrayList<>();

    public List<AwardsDTO> getMin() {
        return min;
    }

    public void setMin(List<AwardsDTO> min) {
        this.min = min;
    }

    public List<AwardsDTO> getMax() {
        return max;
    }

    public void setMax(List<AwardsDTO> max) {
        this.max = max;
    }
}
