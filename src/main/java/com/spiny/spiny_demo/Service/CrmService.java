package com.spiny.spiny_demo.Service;

import com.spiny.spiny_demo.Dto.CustomerDTO;
import com.spiny.spiny_demo.entity.Customer;

public interface CrmService {
    Customer createVisit(CustomerDTO dto);
}
