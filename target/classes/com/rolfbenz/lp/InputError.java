package com.rolfbenz.lp;

/**
 * Created by IntelliJ IDEA.
 * User: U987
 * Date: 04.04.11
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public class InputError {

    private String message;
    private String fieldname;

    public InputError(String message, String fieldname) {
        this.message = message;
        this.fieldname = fieldname;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputError)) return false;

        InputError that = (InputError) o;

        if (fieldname != null ? !fieldname.equals(that.fieldname) : that.fieldname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return fieldname != null ? fieldname.hashCode() : 0;
    }
}
