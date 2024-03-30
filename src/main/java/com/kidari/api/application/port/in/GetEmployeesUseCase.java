package com.kidari.api.application.port.in;

import java.util.List;

public interface GetEmployeesUseCase {
    List<String> getEmployees(Long lecureNo);
}
