// C_PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// eq
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@TRUE_0
D;JEQ
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
// C_PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
// eq
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@TRUE_1
D;JEQ
@SP
A=M
M=0
@END_1
0;JMP
(TRUE_1)
@SP
A=M
M=-1
(END_1)
@SP
M=M+1
// C_PUSH constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// eq
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@TRUE_2
D;JEQ
@SP
A=M
M=0
@END_2
0;JMP
(TRUE_2)
@SP
A=M
M=-1
(END_2)
@SP
M=M+1
// C_PUSH constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 891
@891
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
@TRUE_3
D;JLT
@SP
A=M
M=0
@END_3
0;JMP
(TRUE_3)
@SP
A=M
M=-1
(END_3)
@SP
M=M+1
// C_PUSH constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 892
@892
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
@TRUE_4
D;JLT
@SP
A=M
M=0
@END_4
0;JMP
(TRUE_4)
@SP
A=M
M=-1
(END_4)
@SP
M=M+1
// C_PUSH constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 891
@891
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
@TRUE_5
D;JLT
@SP
A=M
M=0
@END_5
0;JMP
(TRUE_5)
@SP
A=M
M=-1
(END_5)
@SP
M=M+1
// C_PUSH constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// gt
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@TRUE_6
D;JGT
@SP
A=M
M=0
@END_6
0;JMP
(TRUE_6)
@SP
A=M
M=-1
(END_6)
@SP
M=M+1
// C_PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
// gt
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@TRUE_7
D;JGT
@SP
A=M
M=0
@END_7
0;JMP
(TRUE_7)
@SP
A=M
M=-1
(END_7)
@SP
M=M+1
// C_PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// gt
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@TRUE_8
D;JGT
@SP
A=M
M=0
@END_8
0;JMP
(TRUE_8)
@SP
A=M
M=-1
(END_8)
@SP
M=M+1
// C_PUSH constant 57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1
// C_PUSH constant 53
@53
D=A
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
// C_PUSH constant 112
@112
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
// neg
@SP
A=M-1
M=-M
// and
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=D&M
@SP
M=M+1
// C_PUSH constant 82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1
// or
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=D|M
@SP
M=M+1
// not
@SP
A=M-1
M=!M