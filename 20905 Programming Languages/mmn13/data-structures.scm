(module data-structures (lib "eopl.ss" "eopl")

  ;; data structures for let-lang.

  (provide (all-defined))               ; too many things to list

;;;;;;;;;;;;;;;; expressed values ;;;;;;;;;;;;;;;;

;;; an expressed value is either a number, a boolean or a procval.

  (define-datatype expval expval?
    (num-val
      (value number?))
    (bool-val
      (boolean boolean?))
    (cons-val
     (first expval?)
     (rest expval?))
;    (arr-val
;    (list pair?))
    (emptylist-val
     (lst null?))
     )

;;; extractors:

;  (define expval->list
;    (lambda (v)
;      (cases expval v
;        (arr-val (exlist) exlist)
;   (else (expval-extractor-error 'exlist v)))))
  
  ;; expval->num : ExpVal -> Int
  ;; Page: 70
  (define expval->num
    (lambda (v)
      (cases expval v
	(num-val (num) num)
	(else (expval-extractor-error 'num v)))))

  ;; expval->bool : ExpVal -> Bool
  ;; Page: 70
  (define expval->bool
    (lambda (v)
      (cases expval v
	(bool-val (bool) bool)
	(else (expval-extractor-error 'bool v)))))
  
  ;; expval->car : cons-val -> car
  ;; Page: 73
  (define expval->car
    (lambda (val)
      (cases expval val
        (cons-val (first rest) first)
        (else (expval-extractor-error 'cons val)))))
  
  ;; expval->cdr : cons-val -> cdr
  ;; Page: 73
  (define expval->cdr
    (lambda (val)
      (cases expval val
        (cons-val (first rest) rest)
        (else (expval-extractor-error 'cons val)))))
  
  (define expval->emptylist-val
    (lambda () 
      (cases expval
        (emptylist-val () '())
        (else (expval-extractor-error 'emptylist)))))
  
  ;; expval->emptylist : ExpVal -> emptylist
  ;; Page: 73
  (define expval->emptylist?
    (lambda (val)
      (cases expval val
        (emptylist-val (val) #t)
        (cons-val (first rest) #f)
        (else (expval-extractor-error 'emptylist? val)))))
  
  (define expval-extractor-error
    (lambda (variant value)
      (eopl:error 'expval-extractors "Looking for a ~s, found ~s"
	variant value)))

;;;;;;;;;;;;;;;; environment structures ;;;;;;;;;;;;;;;;

;; example of a data type built without define-datatype

  (define empty-env-record
    (lambda () 
      '()))

  (define extended-env-record
    (lambda (sym val old-env)
      (cons (list sym val) old-env)))
  
  (define empty-env-record? null?)
  
  (define environment?
    (lambda (x)
      (or (empty-env-record? x)
          (and (pair? x)
               (symbol? (car (car x)))
               (expval? (cadr (car x)))
               (environment? (cdr x))))))

  (define extended-env-record->sym
    (lambda (r)
      (car (car r))))

  (define extended-env-record->val
    (lambda (r)
      (cadr (car r))))

  (define extended-env-record->old-env
    (lambda (r)
      (cdr r)))

)
