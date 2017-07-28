
sacarlista(Palabra, Lista) :- sinonimo(Palabra, Lista, _).

sacarsinonimo(Palabra, Resultado) :- sacarlista(Palabra, Lista), length(Lista, X), random(0, X, E), nth0(E, Lista, Resultado), sinonimo(Palabra, Lista, _).

determinante(el).
verbo(pelear).
adverbio(mucho).
oracion(A, B, C, Resultado) :- determinante(A), verbo(B), adverbio(C), Resultado = [A, B, C].

sacarOracion(Verbo, A, B, C, Resultado) :- sacarsinonimo(Verbo, X), X2 = [A, X, C], atomic_list_concat(X2, ' ', Resultado).