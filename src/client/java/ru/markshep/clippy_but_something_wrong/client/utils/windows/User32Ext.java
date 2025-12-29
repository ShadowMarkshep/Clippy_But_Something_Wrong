package ru.markshep.clippy_but_something_wrong.client.utils.windows;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;

public interface User32Ext extends Library {
    User32Ext INSTANCE = Native.load("user32", User32Ext.class);
    int MB_OK = 0x00000000;
    int MB_ICONERROR = 0x00000010;
    int MessageBoxW(Pointer hWnd, WString lpText, WString lpCaption, int uType);
}
