// C_PUSH constant 3030
@3030
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_POP pointer 0
@SP
M=M-1
A=M
D=M
@THIS
M=D
// C_PUSH constant 3040
@3040
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_POP pointer 1
@SP
M=M-1
A=M
D=M
@THAT
M=D
// C_PUSH constant 32
@32
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_POP this 2
@2
D=A
@THIS
A=M
D=D+A
@THIS
M=D
@SP
M=M-1
A=M
D=M
@THIS
A=M
M=D
@2
D=A
@THIS
A=M
D=A-D
@THIS
M=D
// C_PUSH constant 46
@46
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_POP that 6
@6
D=A
@THAT
A=M
D=D+A
@THAT
M=D
@SP
M=M-1
A=M
D=M
@THAT
A=M
M=D
@6
D=A
@THAT
A=M
D=A-D
@THAT
M=D
// C_PUSH pointer 0
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH pointer 1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D+M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1
// C_PUSH this 2
@THIS
D=M
@2
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D-M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1
// C_PUSH that 6
@THAT
D=M
@6
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D+M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1
