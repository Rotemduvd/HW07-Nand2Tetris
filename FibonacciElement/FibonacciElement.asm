(Main.fibonacci)
@0
D=A
@R13
M=D
(PUSH_ZERO0)
@R13
D=M
@END_PUSH0
D;JEQ
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@R13
M=M-1
@PUSH_ZERO0
0;JMP
(END_PUSH0)
// C_PUSH argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
// lt
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@TRUE_0
D;JLT
@SP
A=M
M=0
@END_0
0;JMP
(TRUE_0)
@SP
A=M
M=-1
(END_0)
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@N_LT_2
D;JNE
@N_GE_2
0;JMP
(N_LT_2)
// C_PUSH argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@R13
M=D
@5
A=D-A
D=M
@R14
M=D
// C_POP argument 0
@0
D=A
@ARG
A=M
D=D+A
@R13
M=D
@SP
M=M-1
A=M
D=M
@R13
A=M
M=D
@ARG
D=M+1
@SP
M=D
@R13
AM=M-1
D=M
@THAT
M=D
@R13
AM=M-1
D=M
@THIS
M=D
@R13
AM=M-1
D=M
@ARG
M=D
@R13
AM=M-1
D=M
@LCL
M=D
@R14
A=M
0;JMP
(N_GE_2)
// C_PUSH argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 2
@2
D=A
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
// push return address
@RETURN_0
D=A
@SP
A=M
M=D
@SP
M=M+1
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
// C_PUSH argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH this 0
@THIS
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH that 0
@THAT
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// ARG = SP - 5 - nArgs
@SP
D=M
@5
D=D-A
@1
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto Main.fibonacci
@Main.fibonacci
0;JMP
(RETURN_0)
// C_PUSH argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 1
@1
D=A
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
// push return address
@RETURN_1
D=A
@SP
A=M
M=D
@SP
M=M+1
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
// C_PUSH argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH this 0
@THIS
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH that 0
@THAT
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// ARG = SP - 5 - nArgs
@SP
D=M
@5
D=D-A
@1
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto Main.fibonacci
@Main.fibonacci
0;JMP
(RETURN_1)
// add                    // returns fib(n - 1) + fib(n - 2)
@LCL
D=M
@R13
M=D
@5
A=D-A
D=M
@R14
M=D
// C_POP argument 0
@0
D=A
@ARG
A=M
D=D+A
@R13
M=D
@SP
M=M-1
A=M
D=M
@R13
A=M
M=D
@ARG
D=M+1
@SP
M=D
@R13
AM=M-1
D=M
@THAT
M=D
@R13
AM=M-1
D=M
@THIS
M=D
@R13
AM=M-1
D=M
@ARG
M=D
@R13
AM=M-1
D=M
@LCL
M=D
@R14
A=M
0;JMP
(Sys.init)
@0
D=A
@R13
M=D
(PUSH_ZERO1)
@R13
D=M
@END_PUSH1
D;JEQ
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@R13
M=M-1
@PUSH_ZERO1
0;JMP
(END_PUSH1)
// C_PUSH constant 4
@4
D=A
@SP
A=M
M=D
@SP
M=M+1
// push return address
@RETURN_2
D=A
@SP
A=M
M=D
@SP
M=M+1
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
// C_PUSH argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH this 0
@THIS
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH that 0
@THAT
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// ARG = SP - 5 - nArgs
@SP
D=M
@5
D=D-A
@1
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto Main.fibonacci
@Main.fibonacci
0;JMP
(RETURN_2)
(END)
@END
0;JMP
