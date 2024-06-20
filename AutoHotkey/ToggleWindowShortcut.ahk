#Requires AutoHotkey v2.0
#SingleInstance Force

lastWindow := 0

; Minimize Window = Ctrl + Space
^space:: 
{
    global

    if WinExist("A")
    {
        lastWindow := WinExist("A")
        WinMinimize("A")
    }
}

; Restore Window = Ctrl + Shift + Space
^+space:: 
{
    global

    if (lastWindow != 0 && WinExist(lastWindow))
    {
        WinRestore(lastWindow)
        lastWindow := 0
    }
}
