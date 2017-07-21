sol(A):-  sgte(e(1,1),[e(1,1)],_,[],A).

malo(e(1,2)).
malo(e(2,2)).
malo(e(4,2)).
malo(e(1,3)).
malo(e(4,4)).

sgte(e(1,4),V,V,A,A).
sgte(e(X,Y),Lv,V,La,A):- accion(e(X,Y),e(Xs,Ys),Acc),
	                 not(malo(e(Xs,Ys))),
			 not(pertenece(e(Xs,Ys),Lv)),
			 sgte(e(Xs,Ys),[e(Xs,Ys)|Lv],V,[Acc|La],A).

pertenece(X,[X|_]).
pertenece(X,[_|R]):- pertenece(X,R).

%subir
accion(e(X,Y),e(X,Z),'s'):-  Z is Y + 1,
	                 Y < 4.
%bajar
accion(e(X,Y),e(X,Z),'b'):-  Z is Y - 1,
	                 Y > 1.
%derecha
accion(e(X,Y),e(Z,Y),'d'):-  Z is X + 1,
	                 X < 4.
%izquierda
accion(e(X,Y),e(Z,Y),'i'):-  Z is X - 1,
	                 X > 1.

