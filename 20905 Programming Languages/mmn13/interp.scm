(module interp (lib "eopl.ss" "eopl")
  
  ;; interpreter for the LET language.  The \commentboxes are the
  ;; latex code for inserting the rules into the code in the book.
  ;; These are too complicated to put here, see the text, sorry.

  (require "drscheme-init.scm")

  (require "lang.scm")
  (require "data-structures.scm")
  (require "environments.scm")

  (provide value-of-program value-of)

;;;;;;;;;;;;;;;; the interpreter ;;;;;;;;;;;;;;;;

  ;; value-of-program : Program -> ExpVal
  ;; Page: 71
  (define value-of-program 
    (lambda (pgm)
      (cases program pgm
        (a-program (exp1)
          (value-of exp1 (init-env))))))

  ;; value-of : Exp * Env -> ExpVal
  ;; Page: 71
  (define value-of
    (lambda (exp env)
      (cases expression exp

        ;\commentbox{ (value-of (const-exp \n{}) \r) = \n{}}
        (const-exp (num) (num-val num))

        ;\commentbox{ (value-of (var-exp \x{}) \r) = (apply-env \r \x{})}
        (var-exp (var) (apply-env env var))
        
        ;Exercise 3.9
        (cons-exp (exp1 exp2)
          (cons-val (value-of exp1 env) (value-of exp2 env)) )
        
        ;\commentbox{\diffspec}
        (diff-exp (exp1 exp2)
          (let ((val1 (value-of exp1 env))
                (val2 (value-of exp2 env)))
            (let ((num1 (expval->num val1))
                  (num2 (expval->num val2)))
              (num-val
                (- num1 num2)))))
        
        ;Exercise 3.8 multiple
         (mul-exp (exp1 exp2)
          (let ((val1 (value-of exp1 env))
                (val2 (value-of exp2 env)))
            (let ((num1 (expval->num val1))
                  (num2 (expval->num val2)))
              (num-val
                (* num1 num2)))))
 
        ;Exercise 3.8 plus
         (plus-exp (exp1 exp2)
          (let ((val1 (value-of exp1 env))
                (val2 (value-of exp2 env)))
            (let ((num1 (expval->num val1))
                  (num2 (expval->num val2)))
              (num-val
                (+ num1 num2)))))
          
          ;Exercise 3.8 divide
         (div-exp (exp1 exp2)
          (let ((val1 (value-of exp1 env))
                (val2 (value-of exp2 env)))
            (let ((num1 (expval->num val1))
                  (num2 (expval->num val2)))
              (num-val
                (/ num1 num2)))))
          
        ;Exercise 3.8 equal?
        (equal?-exp (exp1 exp2)
          (let ((val1 (value-of exp1 env))
                (val2 (value-of exp2 env)))
            (let ((num1 (expval->num val1))
                  (num2 (expval->num val2)))
              (if (eqv? num1 num2)
                (bool-val #t)
                (bool-val #f)))))
          
        ;Exercise 3.8 greater?
        (greater?-exp (exp1 exp2)
          (let ((val1 (value-of exp1 env))
                (val2 (value-of exp2 env)))
            (let ((num1 (expval->num val1))
                  (num2 (expval->num val2)))
              (if (> num1 num2)
                (bool-val #t)
                (bool-val #f)))))
        
        ;Exercise 3.8 less?
        (less?-exp (exp1 exp2)
          (let ((val1 (value-of exp1 env))
                (val2 (value-of exp2 env)))
            (let ((num1 (expval->num val1))
                  (num2 (expval->num val2)))
              (if (< num1 num2)
                (bool-val #t)
                (bool-val #f)))))
        
        ;Exercise 3.9 car
        (car-exp (exp)
         (let ((val (value-of exp env)))
           (expval->car val)))
        
        ;Exercise 3.9 cdr
        (cdr-exp (exp)
         (let ((val (value-of exp env)))
           (expval->cdr val)))
        
        ;Exercise 3.9 null?
        (null?-exp (exp)
          (let ((val (value-of exp env)))
            (let ((bool (expval->emptylist? val)))
              (bool-val bool))))
        
         ;Exercise 3.9 emptylist
        (emptylist-exp ()
           (emptylist-val '() ))
        
        ;\commentbox{\zerotestspec}
        (zero?-exp (exp1)
          (let ((val1 (value-of exp1 env)))
            (let ((num1 (expval->num val1)))
              (if (zero? num1)
                (bool-val #t)
                (bool-val #f)))))
              
        ;\commentbox{\ma{\theifspec}}
        (if-exp (exp1 exp2 exp3)
          (let ((val1 (value-of exp1 env)))
            (if (expval->bool val1)
              (value-of exp2 env)
              (value-of exp3 env))))

        ;\commentbox{\ma{\theletspecsplit}}
        (let-exp (var exp1 body)       
          (let ((val1 (value-of exp1 env)))
            (value-of body
              (extend-env var val1 env))))
 ;arbno
        (array-exp (exp exps)
           (if (null? exp)
              (emptylist-val '())
              (if (null? exps)
                  (cons (value-of exp env) (emptylist-val '() ))
                  (cons (value-of exp env) (value-of (array-exp (car exps) (cdr exps)) env) ))))

;separated-list                  
;        (array-exp (exps)
;           (if (null? exps)
;;               (eopl:error 'array-exp "no allowed empty array: ~s" exps)
;               (emptylist-val '())
;;               (array-exp-helper (exps env))))
;               (let 
;                   ((val1 (value-of (car exps) env))
;                   (val2  (value-of (array-exp (cdr exps)) env)))
;   ;                (cons-val val1 val2))))
;                   (cons val1 val2))))
        
        (index-exp (arr index)
             (list-ref  (value-of arr env) (- (expval->num (value-of index env)) 1)))

        )))

;  (define array-exp-helper
;    (lambda (exps env)
;           (if (null? exps)
;               (emptylist-val '())
;               (let 
;                   ((val1 (value-of (car exps) env))
;                   (val2  (value-of (array-exp-helper (cdr exps) env) env)))
;   ;                (cons-val val1 val2))))
;                   (cons val1 val2)))))

  )

