package com.eccomerce.Ecart.Exceptions;
public class ResourceNotFoundException extends RuntimeException{
    String Resource;
    String FieldName;
    String Field;
    Long Id;

    public ResourceNotFoundException(String resource, String fieldName, String field) {
        super(String.format("%s is not found %s at %s",resource,fieldName,field));
        this.Resource = resource;
        this.FieldName = fieldName;
        this.Field = field;
    }

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String resource, String field, Long id) {
        super(String.format("%s is not found %s at %s",resource,id,field));
        Resource = resource;
        Field = field;
        Id = id;
    }
}
