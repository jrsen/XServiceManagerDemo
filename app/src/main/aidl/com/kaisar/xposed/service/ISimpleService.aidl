package com.kaisar.xposed.service;

interface ISimpleService{
    String readPackageXml();
    boolean saveConfig(String path, String config);
}