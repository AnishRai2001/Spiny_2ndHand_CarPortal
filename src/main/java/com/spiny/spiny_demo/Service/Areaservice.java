package com.spiny.spiny_demo.Service;

import com.spiny.spiny_demo.Dto.AreaDto;
import com.spiny.spiny_demo.entity.Area;

public interface Areaservice {

    public Area findByPincode(int pincode);
    Area saveArea(AreaDto dto);
}
