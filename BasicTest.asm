// C_PUSH constant 10
@10
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_POP local 0
@0
D=A
@LCL
A=M
D=D+A
@LCL
M=D
@SP
M=M-1
A=M
D=M
@LCL
A=M
M=D
@0
D=A
@LCL
A=M
D=A-D
@LCL
M=D
// C_PUSH constant 21
@21
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 22
@22
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_POP argument 2
@2
D=A
@ARG
A=M
D=D+A
@ARG
M=D
@SP
M=M-1
A=M
D=M
@ARG
A=M
M=D
@2
D=A
@ARG
A=M
D=A-D
@ARG
M=D
// C_POP argument 1
@1
D=A
@ARG
A=M
D=D+A
@ARG
M=D
@SP
M=M-1
A=M
D=M
@ARG
A=M
M=D
@1
D=A
@ARG
A=M
D=A-D
@ARG
M=D
// C_PUSH constant 36
@36
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_POP this 6
@6
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
@6
D=A
@THIS
A=M
D=A-D
@THIS
M=D
// C_PUSH constant 42
@42
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 45
@45
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_POP that 5
@5
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
@5
D=A
@THAT
A=M
D=A-D
@THAT
M=D
// C_POP that 2
@2
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
@2
D=A
@THAT
A=M
D=A-D
@THAT
M=D
// C_PUSH constant 510
@510
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_POP temp 6
@6
D=A
@5
D=D+A
@SP
M=M-1
A=M
D=M
@11
M=D
// C_PUSH local 0
@LCL
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH that 5
@THAT
D=M
@5
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
// C_PUSH argument 1
@ARG
D=M
@1
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
// C_PUSH this 6
@THIS
D=M
@6
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH this 6
@THIS
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
// C_PUSH temp 6
@11
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
