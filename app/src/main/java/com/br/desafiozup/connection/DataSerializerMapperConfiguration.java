package com.br.desafiozup.connection;

public class DataSerializerMapperConfiguration {
    private String formatDate;

    public DataSerializerMapperConfiguration(String formatDate) {
        this.formatDate = formatDate;
    }

    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

}
