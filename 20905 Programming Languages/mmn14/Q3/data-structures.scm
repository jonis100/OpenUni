(module data-structures (lib "eopl.ss" "eopl")

  (require "lang.scm")                  ; for expression?

  (provide (all-defined))               ; too many things to list

;;;;;;;;;;;;;;;; expressed values ;;;;;;;;;;;;;;;;

;;; an expressed value is either a number, a boolean or a procval.

  (define-datatype expval expval?
    (num-val
      (value number?))
    (bool-val
      (boolean boolean?))
    (proc-val 
      (proc proc?)))

;;; extractors:

  ;; expval->num : ExpVal -> Int
  (define expval->num
    (lambda (v)
      (cases expval v
	(num-val (num) num)
	(else (expval-extractor-error 'num v)))))

  ;; expval->bool : ExpVal -> Bool
  (define expval->bool
    (lambda (v)
      (cases expval v
	(bool-val (bool) bool)
	(else (expval-extractor-error 'bool v)))))

  ;; expval->proc : ExpVal -> Proc
  (define expval->proc
    (lambda (v)
      (cases expval v
	(proc-val (proc) proc)
	(else (expval-extractor-error 'proc v)))))

  (define expval-extractor-error
    (lambda (variant value)
      (eopl:error 'expval-extractors "Looking for a ~s, found ~s"
	variant value)))

;;;;;;;;;;;;;;;; procedures ;;;;;;;;;;;;;;;;

  ;; proc? : SchemeVal -> Bool
  ;; procedure : Var * Exp * Env -> Proc
  (define-datatype proc proc?
    (procedure
      (ids listofsymbols?)
      (defs listofexpval?)
      (body expression?)
      (env environment?)))
  
  (define listofsymbols?
     (lambda (lst)
         (if (null? lst)
             #t
             (and (symbol? (car lst)) (listofsymbols? (cdr lst))))))
  
    (define listofexpval?
     (lambda (lst)
         (if (null? lst)
             #t
             (and (expval? (car lst)) (listofexpval? (cdr lst))))))
  
;;;;;;;;;;;;;;;; environment structures ;;;;;;;;;;;;;;;;
  (define-datatype environment environment?
    (empty-env-record)
    (extended-env-record
     (syms (list-of symbol?))
     (vals (list-of expval?))
     (env environment?))
    )
  
  (define env-find-position 
    (lambda (sym los)
      (list-find-position sym los)))
  
  (define list-find-position
    (lambda (sym los)
      (list-index (lambda (sym1) (eqv? sym1 sym)) los)))
  
  (define list-index
    (lambda (pred ls)
      (cond
        ((null? ls) #f)
        ((pred (car ls)) 0)
        (else (let ((list-index-r (list-index pred (cdr ls))))
                (if (number? list-index-r)
                    (+ list-index-r 1)
                    #f))))))
  
  (define apply-env-ref
    (lambda (env search-sym)
      (cases environment env
        (empty-env-record () (eopl:error 'apply-env "no association for symbol ~s" search-sym))
        (extended-env-record (syms vals old_env)
                             (let ((position (env-find-position search-sym syms)))
                               (if (number? position)
                                   (list-ref vals position)
                                   (apply-env-ref old_env search-sym)))))))  
)
