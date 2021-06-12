package com.ursa.app.timesheet.controller.v1.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserListRequest extends ListRequest {
    private String search;
}
