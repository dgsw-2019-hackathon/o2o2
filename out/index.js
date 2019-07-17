"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const console_stamp_1 = __importDefault(require("console-stamp"));
const express_1 = __importDefault(require("express"));
const body_parser_1 = __importDefault(require("body-parser"));
//import 끗
const PORT = 8420;
const IP = "0.0.0.0";
//상수선언 끗
var _cs = console_stamp_1.default(console, { pattern: "[HH:MM:ss.l]" });
var _ex = express_1.default();
var _value = 0;
var _door = 0; //0 아무 상황 아님 1 경고
//변수선언 끗
_ex.use(body_parser_1.default.json());
_ex.get("/", _fget);
function _fget(req, res) {
    console.log("_fget : " + req.ip);
    res.status(200).send("HeLL0, W0rLd!");
}
//get 들어오면 헬로월드 띄우는 함수 끗
_ex.get("/value", _fvalue);
function _fvalue(req, res) {
    console.log("_fvalue : " + req.ip);
    res.status(200).send(String(_value));
}
//값 띄우는 함수 끗
_ex.get("/door", _fgetDoor);
function _fgetDoor(req, res) {
    console.log("_fgetDoor : " + req.ip);
    res.status(200).send(String(_door));
}
//문 데이터 띄우는 함수 끗
_ex.post("/set", _fset);
function _fset(req, res) {
    console.log("_fset : " + req.ip);
    console.log("   " + JSON.stringify(req.body));
    var body = req.body;
    var value = Number(body.value);
    //지역변수 선언 끗
    if (value == 1) {
        setTimeout(_fplanB, 3000);
    }
    _value = value;
    res.status(201).send("value changed = " + _value);
}
//값 바꾸는 함수 끗
function _fplanB() {
    _value = 0;
}
//센서값 들어오고 1초뒤 원상복구 시키는 함수 끗
_ex.post("/door", _fdoor);
function _fdoor(req, res) {
    console.log("_fdoor : " + req.ip);
    console.log("   " + JSON.stringify(req.body));
    var body = req.body;
    var value = Number(body.value);
    //지역변수 선언 끗
    if (value == 1) {
        setTimeout(_f, 3000);
    }
    _door = value;
    res.status(201).send("door changed = " + _door);
}
//문 제어 함수 끗
function _f() {
    _door = 0;
}
//문 데이터 오고 1초뒤 원상복구 시키는 함수 끗
_ex.listen(PORT, IP, _flisten);
function _flisten() {
    console.log("서버가 성공적으로 열림");
}
//서버온
